package com.youpu.architecture.proxy;

public class Proxy {
    private Consumer consumer;

    public Proxy(Consumer consumer) {
        this.consumer = consumer;
    }

    public void buyHouse(){
        consumer.sellHouse();
    }
}
