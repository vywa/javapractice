package com.youpu.architecture.proxy;

public class App {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Proxy proxy = new Proxy(consumer);
        proxy.buyHouse();
    }
}
