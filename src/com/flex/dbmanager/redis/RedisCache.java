package com.flex.dbmanager.redis;

import com.flex.utils.Constants;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;
import com.flex.utils.SecretCards;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RedisCache  {  
   /** 
    *  
   * @ClassName: CachePool 
   * @Description: 
   * @author Tich Nguyen 
   * @date 12/07/2016 
   * 
    */  
    public static class CachePool {
	   private static final CachePool cachePool = new CachePool();
	   public static Map _config = null;
	   private static HashMap<String, CachePool> mapPool = new HashMap<String, CachePool>();
	   String DB_CONFIG_FILENAME = "properties.ini";
	    	String DB_CATEGORY_NAME = "Redis Configuration";
	    	int timeoutReconnect = 30000;// doi 30s se timeout
	    	int connectionTimeout=Protocol.DEFAULT_TIMEOUT;
	    	int soTimeout=Protocol.DEFAULT_TIMEOUT;
	    	String host= Protocol.DEFAULT_HOST;
	    	int hostPort=Protocol.DEFAULT_PORT;
	    	String password;
	    	int database= Protocol.DEFAULT_DATABASE;
	    	int maxIdle=100;
	    	int minIdle=10;
	    	int maxTotal=100;
	    	int maxWaitMillis=30000;
	    	String clientName = "V4";
            JedisPool pool;

	   private CachePool() {
		   LoadConfig();
//                    JedisPoolConfig config = new JedisPoolConfig();
//                    config.setMaxIdle(maxIdle);
//                    config.setMinIdle(minIdle);
//                    config.setMaxTotal(maxTotal);
//                    config.setMaxWaitMillis(maxWaitMillis);
//                     pool = new JedisPool(config, host,hostPort,
//                    		 connectionTimeout, soTimeout, password,
//                    		 database,null);

            	//using for jedis 2.9
            	 GenericObjectPoolConfig config=new  GenericObjectPoolConfig();
		   config.setMaxIdle(maxIdle);
		   config.setMinIdle(minIdle);
	               config.setMaxTotal(maxTotal);
	               config.setMaxWaitMillis(maxWaitMillis);
	               pool = new JedisPool(config, host, hostPort,
						   connectionTimeout, password, database, clientName);
	   }

	   private CachePool(Map pro_config) {
            	LoadConfig();
            	if(null!=pro_config){
					host = pro_config.get("host").toString();
					hostPort=Integer.valueOf(pro_config.get("port").toString());
	            	password=String.valueOf(pro_config.get("password"));
	            	database = Integer.valueOf(pro_config.get("database").toString());
            	}
            	 GenericObjectPoolConfig config=new  GenericObjectPoolConfig();
				config.setMaxIdle(maxIdle);
		   config.setMinIdle(minIdle);
	               config.setMaxTotal(maxTotal);
	               config.setMaxWaitMillis(maxWaitMillis);
	               pool = new JedisPool(config, host, hostPort,
						   connectionTimeout, password, database, clientName);
			}

	   public static CachePool getInstance() {
		   if (null != _config) {
			   String mapKey = String.valueOf(_config.get("host")) + String.valueOf(_config.get("port")) + String.valueOf(_config.get("database"));
			   if (null == mapPool.get(mapKey)) {
				   return CachePool.getInstance(_config);
			   }
			   return mapPool.get(mapKey);
		   } else {
			   return cachePool;
		   }
	   }

	   public static CachePool getInstance(Map config) {
		   CachePool pool = new CachePool(config);
		   String mapKey = pool.host + pool.hostPort + pool.database;//String.valueOf(_config.get("host"))+String.valueOf(_config.get("port"))+String.valueOf(_config.get("database"));
		   mapPool.put(mapKey, pool);
		   return pool;
	   }

	   public synchronized Jedis getJedis() {
		   Jedis jedis = null;
//                    try {  
//                            jedis = pool.getResource();  
//                    } catch (JedisConnectionException e) {    
//                            if (jedis != null)  
//                            	jedis.close();  
//                    } finally {  
//                    	if (jedis != null)  
//                        	jedis.close();    
//                    }  
                    jedis = pool.getResource();  
                   
                    return jedis;  
            }  
              
            public JedisPool getJedisPool(){  
                    return this.pool;  
            }  
            /**
             * load config
             */
             void LoadConfig() {
        		
        			String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
        			String dbConfigPath = Lib.combine(path, DB_CONFIG_FILENAME);

        			host = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "host", "127.0.0.1",
        					String.class);

        			hostPort = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "port", "6379",
        					Integer.class);

        			database = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "database", "0",
        					Integer.class);

        			String pass = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "password", "", String.class);
        			password = Lib.isBlank(pass) ? null : Lib.safeTrim(pass);

        			clientName = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "clientName",
        					"V4", String.class);
        			connectionTimeout=IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "connectionTimeout", "2000",
        					Integer.class);
        			soTimeout=IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "soTimeout", "2000",
        					Integer.class);
        			maxIdle=IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "maxIdle", "100",
        					Integer.class);
        			minIdle=IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "minIdle", "10",
        					Integer.class);
        			
        			maxTotal=IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "maxTotal", "100",
        					Integer.class);
        			maxWaitMillis=IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "maxWaitMillis", "30000",
        					Integer.class);
        			String encrypt = Lib.safeTrim(
        					IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "encrypt", "", String.class));
        			if (!"false".equalsIgnoreCase(encrypt)) {
        				SecretCards secret = new SecretCards();
        				password = secret.decrypt(password);
        			}
        		
        		
        	}
    }
    /**
     * 
     * @author tichnguyen
     *
     */
    public static class SerializeUtil {  
            public static byte[] serialize(Object object) {  
                    ObjectOutputStream oos = null;  
                    ByteArrayOutputStream baos = null;  
                    try {  
                            
                            baos = new ByteArrayOutputStream();  
                            oos = new ObjectOutputStream(baos);  
                            oos.writeObject(object);  
                            byte[] bytes = baos.toByteArray();  
                            return bytes;  
                    } catch (Exception e) {  
                            e.printStackTrace();  
                    }  
                    return null;  
            }  

            public static Object unserialize(byte[] bytes) {  
                    if(bytes == null)return null;  
                    ByteArrayInputStream bais = null;  
                    try {  
                            
                            bais = new ByteArrayInputStream(bytes);  
                            ObjectInputStream ois = new ObjectInputStream(bais);  
                            return ois.readObject();  
                    } catch (Exception e) {  
                            e.printStackTrace();  
                    }  
                    return null;  
            }  
    }  
}
