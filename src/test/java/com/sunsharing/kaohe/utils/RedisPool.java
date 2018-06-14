/*
 * @(#) RedisPool
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-13 14:57:17
 */

package com.sunsharing.kaohe.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    //jedis连接池
    private static JedisPool pool ;
     //最大连接数
    private static Integer maxTotal = 20;
    //最小空闲实例个数
    private static Integer minIdle = 2;
    //最大空闲实例个数
    private static Integer maxIdle= 10;
   //从redis连接池中借实例时。是否进行验证
    private static Boolean testOnBorrow = true;
    private static Boolean testOnReturn = true;

    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        //连接耗尽时是否阻塞
        jedisPoolConfig.setBlockWhenExhausted(true);

        pool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379,1000*2);
    }

    static {
        initPool();
    }

    public static Jedis getPool(){
      return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);

    }

    public static void main(String[] args) {

    }
}