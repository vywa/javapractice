package com.youpu.service.impl;

import com.youpu.domain.User;
import com.youpu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable(cacheNames = "cache_test",key="#user.id")
    @Override
    public User getUser(User user) {
        User user1 = new User();
        user1.setId("1111");
        user1.setUsername("baoan");
        user1.setPassword("hahaha");
        logger.info("从数据库获取到数据");
        return user1;
    }

    @CachePut()
    @Override
    public int addUser(User user) {
        return 0;
    }

    @CacheEvict()
    @Override
    public int delUser(User user) {
        return 0;
    }


}
