package com.youpu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.youpu.service.UserAccountService;
import org.springframework.stereotype.Service;

@Service("myService")
public class UserAccountServiceImpl {

    @Reference(interfaceName = "com.youpu.service.UserAccountService")
    private UserAccountService userAccountService;


    public void sayHello() {
        int data = userAccountService.add();
        System.out.println("返回结果是"+data);
    }
}
