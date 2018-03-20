package com.youpu.utils;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;


import java.io.Serializable;

public class RedisCache implements Cache{

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private RedisTemplate<String,Object> redisTemplate;
    private String name;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    /**
     * 获取缓存
     * @param o
     * @return
     */
    @Override
    public ValueWrapper get(Object o) {
        final String keyf = o.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = redisConnection.get(key);
                if(null == value){
                    logger.info("缓存不存在");
                    return null;
                }
                return SerializationUtils.deserialize(value);
            }
        });
        ValueWrapper obj = (object!=null ? new SimpleValueWrapper(object):null);
        logger.info("获取到内容"+obj);
        return obj;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    /**
     * 加入缓存
     * @param key
     * @param value
     */
    @Override
    public void put(Object key, Object value) {
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;

        redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
                redisConnection.set(keyb,valueb);
                if(liveTime >0 ){
                    redisConnection.expire(keyb,liveTime);
                }
                logger.info("加入缓存成功");
                return 1L;
            }
        });

    }


    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    /**
     * 缓存删除
     * @param key
     */
    @Override
    public void evict(Object key) {
        final String kekf= key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.del(kekf.getBytes());
            }
        });
    }

    /**
     * 清除数据
     */
    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback<String>() {

            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return "ok";
            }
        });
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }
}
