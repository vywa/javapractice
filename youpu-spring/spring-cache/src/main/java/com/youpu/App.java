package com.youpu;

import com.youpu.domain.User;
import com.youpu.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-cache.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setId("1111");
        userService.getUser(user);
        userService.getUser(user);

    }
}
