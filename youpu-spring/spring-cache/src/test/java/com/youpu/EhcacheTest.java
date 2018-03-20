package com.youpu;

import com.youpu.domain.User;
import com.youpu.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EhcacheTest extends SpringTestCase{

    @Autowired
    private UserService userService;

    @Test
    public void getUser(){
        User user = new User();
        user.setId("1111");
        User result = userService.getUser(user);

        System.out.println("用户信息为"+ result.getUsername());
        User result2 = userService.getUser(user);
        System.out.println("用户信息为"+ result2.getUsername());


    }
}
