package com.flex.dbmanager.redis;

import com.flex.utils.FLLogger;

import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;


public class MyJedisPool {
	public static final FLLogger log = FLLogger.getLogger("redis/log");
    public static JedisPool pool = null;
    public static Jedis jedis = null;

    public MyJedisPool(String redisHost,int redisPort,String resisPassword) {
        pool = new JedisPool(redisHost,redisPort);
        jedis = pool.getResource();
        jedis.auth(resisPassword);
    }
    public Long lpush(String key, String value){
        return jedis.lpush(key, value);
    }
    public String lpop(String key){
        return jedis.lpop(key);
    }
    public String lpop_commit(String key) {
        try {

            String value = jedis.lpop(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String lpop_rollback(String key) {
        try {
            String value =  jedis.lpop(key);
            //Transaction t = jedis.multi();
            //t.watch(key);
            //Response<String> string = t.get("email");
            //t.lpop(key);
            //List<Object> list = t.exec();
            //t.discard();
            //t.close();
//            if(list ==null){
//                return "";
//            }
            return value;
            //String s = value.get();
            //t.exec();
            //t.discard();
            //t.discard();




        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}