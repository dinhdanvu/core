package com.flex.dbmanager.redis;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.flex.utils.FLLogger;
import com.flex.utils.Lib;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisClusterPool {
//    private static FLLogger log;
	public static final FLLogger log = FLLogger.getLogger("redis/log");
    private static JedisCluster jedisCluster = null;
    private static Thread shutdown = null;

    

    private boolean warmUp; // create and delete some random keys to force connections to be made

    private boolean blockWhenExhausted; // true: If we're out of resources, wait for some; false: fail

//    private String hosts = null;

//    private String DB_CONFIG_FILENAME = "properties.ini";
//    private String DB_CATEGORY_NAME = "Redis Configuration";
	private static RedisParamEntity param = new RedisParamEntity("properties.ini","Redis Configuration");
    
    private int maxAttempts=5;
    public static Map _config =null;
    private static HashMap<String, JedisCluster> mapPool = new HashMap<String, JedisCluster>();

//    public static class ClusterCachePool {  
//    	 public static JedisCluster getInstance(){
//    		 if(null!=_config){
//    	    		String mapKey = String.valueOf(_config.get("host"));
//    	    		return mapPool.get(mapKey);
//    	    	}else{
//    	            return jedisCluster;
//    	    	}
//    	 }
//    }
    
    public JedisClusterPool() {
    	if(null!=_config){
    		if(null==mapPool){
    			init();
    		}
    	}else{
    		if(null==jedisCluster){
    			init();
    		}
    	}
    }


    private synchronized void init() {
            Map<String, JedisPool> nodes = null;
            try {
                String[] jcHosts = param.host.split("\\s*,\\s*");
                
            	if(null!=_config){
            		Object objHost = _config.get("host");
            		param.password = Lib.str(_config.get("password")) ;
            		if(null!=objHost){
            			jcHosts=objHost.toString().split("\\s*,\\s*");
            		}
            	}
                
                Set<HostAndPort> jedisClusterNodes = new HashSet<>();

                for (String jcHost : jcHosts) {
                    String[] parts = jcHost.split("\\s*:\\s*");
                    try {
                        InetAddress address = InetAddress.getByName(parts[0]);
                        jedisClusterNodes.add(new HostAndPort(address.getHostAddress(), Integer.valueOf(parts[1])));
                    }
                    catch (Exception e) {
                        log.warn("Problem parsing jedis cluster host name: " + jcHost);
                    }
                }

                if (jedisClusterNodes.size() == 0) {
                    log.fatal("No usable jedis cluster host addresses found");
                    //System.exit(1);
                }
                else {
                    GenericObjectPoolConfig config = initPoolConfiguration();
                    config.setMaxIdle(param.maxIdle);
                    config.setMinIdle(param.minIdle);
                    config.setMaxWaitMillis(param.maxWaitMillis);
                    config.setMaxTotal(param.maxTotal);
//                    config.getNumTestsPerEvictionRun();
                    
                    if(null!=_config){
                    	String mapKey = String.valueOf(_config.get("host"));
//                    	JedisCluster jCluster = new JedisCluster(jedisClusterNodes, param.connectionTimeout, param.soTimeout,maxAttempts,param.password,config);
                    	JedisCluster jCluster = new JedisCluster(jedisClusterNodes, param.connectionTimeout, param.soTimeout,maxAttempts,config);
                    	mapPool.put(mapKey, jCluster);
//                    	nodes = jCluster.getClusterNodes();
//                        log.info("Cluster members: " + nodes.keySet());
                    }else{
                    	jedisCluster = new JedisCluster(jedisClusterNodes, param.connectionTimeout, param.soTimeout,maxAttempts,param.password,config);
//                        nodes = jedisCluster.getClusterNodes();
//                        log.info("Cluster members: " + nodes.keySet());
                    }
                }
            }
            catch (Exception e) {
                log.fatal("Failed to create JedisCluster: " + e, e);
//                System.exit(1);
            }

            if (shutdown == null) {
                shutdown = new Thread(new Runnable() {
                    // Clean up at exit
                    @Override
                    public void run() {
                        log.info(JedisClusterPool.class.getSimpleName() + " shutdown");

                        try {
                            if (jedisCluster != null) {
//                            	log.info("======== jedisCluster shutdown init=========");
//                            	comment de test @jincheng
//                                jedisCluster.close();
                            }
                        }
                        catch (Exception e) {
                            log.error(e);
                        }
                        finally {
//                            jedisCluster = null;
                        }
                    }
                });

                Runtime.getRuntime().addShutdownHook(shutdown);
            }

            if (jedisCluster != null && warmUp) {
                try {
                    log.info(JedisClusterPool.class.getSimpleName() + " warmup");

                    final ExecutorService executor = Executors.newCachedThreadPool();
                    try {
                        for (int i = 0; i < (param.poolSize * (nodes == null ? 1 : nodes.size())); i++) {
                            final String key = JedisClusterPool.class.getSimpleName() + ":" + i;
                            executor.submit(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        jedisCluster.set(key, key);
                                        jedisCluster.expire(key, 1);
                                    }
                                    catch (Exception e) {
                                        log.warn(e.getMessage());
                                    }
                                }
                            });
                        }
                    }
                    finally {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException e) {
                            // ignore
                        }

                        executor.shutdown();
                        try {
                            executor.awaitTermination(5L, TimeUnit.SECONDS);
                        }
                        catch (Exception e) {
                            // ignore
                        }
                        finally {
                            if (!executor.isTerminated()) {
                                executor.shutdownNow();
                            }
                        }
                    }
                }
                catch (Exception e) {
                    log.fatal("Could not start " + JedisClusterPool.class.getSimpleName(), e);
                    //System.exit(1);
                }
            }

            if (jedisCluster != null) {
//                log.info(JedisClusterPool.class.getSimpleName() + " is up");
            }
//        }
    }
    
    private GenericObjectPoolConfig initPoolConfiguration() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        config.setLifo(true);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);
        config.setBlockWhenExhausted(blockWhenExhausted);
        
        config.setMaxTotal(param.poolSize + 1); // the number of Jedis connections to create
        config.setTestWhileIdle(false);
        config.setSoftMinEvictableIdleTimeMillis(3000L);
        config.setNumTestsPerEvictionRun(5);
        config.setTimeBetweenEvictionRunsMillis(5000L);
        config.setJmxEnabled(true);
     // config.setMinIdle(Math.max(1, (int) (poolSize / 10))); // keep 10% hot always
        config.setMinIdle(param.minIdle);
        config.setMaxIdle(param.maxIdle);  
        config.setMaxWaitMillis(param.maxWaitMillis);
        

        return config;
    }

    public JedisCluster acquire() throws Exception {
    	if(null!=_config){
    		String mapKey = String.valueOf(_config.get("host"));
    		if(null==mapPool.get(mapKey)){
    			init();
    		}
    		return mapPool.get(mapKey);
    	}else{
    		try{
	    		if(null==jedisCluster||jedisCluster.getClusterNodes().size()<=0){
	    			init();
	    		}
//	    		log.debug("Cluster members: " + jedisCluster.getClusterNodes().keySet());
	            return jedisCluster;
    		}catch (Exception e) {
    			log.error("acquire",e);
    			init();
    			return jedisCluster;
			}
    	}
//        return jedisCluster;
    }

    public static void release(JedisCluster connection) throws Exception {
        // noop; but it used to do something when we had our own pools
    }

    public String getHosts() {
        return param.host;
    }

    public void setHosts(String hosts) {
        param.host = hosts;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (jedisCluster != null) {
//            	log.info("======== jedisCluster shutdown finalize=========");
//                jedisCluster.close();
            }
        }
        catch (Exception e) {
            // ignore
        }
        finally {
//            jedisCluster = null;
        }

        super.finalize();
    }

}