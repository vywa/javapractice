package com.youpu;

import com.youpu.service.Producer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Producer producer = (Producer) applicationContext.getBean("producer");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("data","hello,mq");
        producer.sendMessage("test_mq_exchange","test_mq_part",map);
    }
}
