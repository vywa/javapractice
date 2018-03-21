package com.youpu.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消息队列发送者
 */
@Component("producer")
public class Producer {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String exchange_key,String queue_key,Object object){
        //将java对象转换为消息发送至匹配key的交换机exchange中
        amqpTemplate.convertAndSend(exchange_key,queue_key,object);
    }
}
