package com.youpu.service.impl;

import com.youpu.domain.User;
import com.youpu.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService{

    //标注该方法查询结果进入缓存，再次访问直接从缓存中取
    @Cacheable("findByName")
    @Override
    public User findByName(String username) {
        User user = new User();
        user.setUsername("baoan");
        user.setPassword("111111");
        user.setBirthday(new Date());
        System.out.println("这是查询数据库的结果");
        return user;
    }

    @CacheEvict(value={"findByName"},allEntries = true)
    @Override
    public void updateUser(User user) {

    }
}
