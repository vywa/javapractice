package com.youpu.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component("rabbitmqService")
public class RabbitmqService implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("消费消息:"+message.toString());
    }
}
