package com.youpu.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component("consumerService")
public class ConsumerService {

    //将消费者由类级别改为方法级别
    public void getMessage(Map<String,Object> map){
        System.out.println(" 方法消费"+map);
    }
}
