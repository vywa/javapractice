# spring整合redis

## 添加依赖
```
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-redis</artifactId>
</dependency>

```

## redis.properties
```
# Redis settings  
redis.host=127.0.0.1
redis.port=6379  
#redis.pass=password
redis.dbIndex=0  
redis.expiration=3000  
redis.maxIdle=300  
redis.maxActive=600  
redis.maxWait=1000  
redis.testOnBorrow=true
```
## 启动redis
通过指定配置文件启动

```
redis-server /etc/redis.conf

```

## 配置redis-spring.xml



通过spring管理redis缓存配置

RedisCacheConfig: 需要增加这个配置类，会在applicationContex配置文件中注册这个bean。



 
