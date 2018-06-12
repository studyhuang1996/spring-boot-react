/*
 * @(#) RedisUtils
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2018
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author huang
 * <br> 2018-06-08 10:19:51
 */

package com.sunsharing.kaohe.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisUtils {

    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 写入缓存
     * @param key
     * @param object
     * @return
     */
    public  boolean set(final String key,Object object){
        boolean result = false;
        try {
            ValueOperations<String,Object> operations = redisTemplate.opsForValue();
            operations.set(key,object);
            result =true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 增加设置缓存时效
     * @param key
     * @param object
     * @param expireTime
     * @return
     */
    public boolean set(final String key,Object object,Long expireTime){
        boolean result = false;
        try {
            ValueOperations<String,Object> operations = redisTemplate.opsForValue();
            operations.set(key,object);
            redisTemplate.expire(key,expireTime, TimeUnit.DAYS);
            result =true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final  String key){
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 获取所有的key
     */
    public Set<String> keys() {
        return redisTemplate.keys("*");
    }


    /**
     * 批量删除对应的value
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public  boolean delete(final String key){
      if (exists(key)){
          redisTemplate.delete(key);
          return true;
      }
      return false;
    }

    /**
     * 是否存在
     * @param key
     * @return
     */
    public boolean exists(final String key){
        return  redisTemplate.hasKey(key);
    }
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
