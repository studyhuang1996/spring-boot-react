/*
 * @(#) RedisPoolUtil
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-13 15:00:00
 */

package com.sunsharing.kaohe.utils;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisPoolUtil {

    /**
     * session设置过期时间
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public static  String setEX(String key,String value,int expire){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getPool();
            result = jedis.setex(key,expire,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static  Long expire(String key,int expire){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getPool();
            result = jedis.expire(key,expire);
        } catch (Exception e) {

            e.printStackTrace();
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * set
     * @param key
     * @param value
     * @return
     */
    public static  String set(String key,String value){
        Jedis jedis = null;
        String result = null;
        try {
             jedis = RedisPool.getPool();
            result = jedis.set(key,value);

        } catch (Exception e) {

            e.printStackTrace();

        }
         RedisPool.returnResource(jedis);
        return result;
    }

    public static  String get(String key){
        Jedis jedis =null;
        String result = null;
        try {
            jedis= RedisPool.getPool();
            result = jedis.get(key);

        } catch (Exception e) {
            e.printStackTrace();
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static  Long del(String key){
        Jedis jedis =null;
        Long result = null;
        try {
            jedis = RedisPool.getPool();
            result = jedis.del(key);

        } catch (Exception e) {
            e.printStackTrace();
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        set("123key","121212");
        System.out.println(get("123key"));
        setEX("key1","value1",60);
        expire("key2",60);
        System.out.println( get("key1"));
    }
}
