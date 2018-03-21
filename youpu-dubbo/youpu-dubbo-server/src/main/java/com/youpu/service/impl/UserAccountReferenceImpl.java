package com.youpu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.youpu.service.UserAccountService;

import java.util.List;

@Service(interfaceName = "com.youpu.service.UserAccountService")
public class UserAccountReferenceImpl implements UserAccountService {

    @Override
    public int add() {
        int data = 0;
        for(int i=0;i<100;i++){
            data +=i;
        }
        return data;
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<String> findList() {
        return null;
    }
}
