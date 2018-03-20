package com.youpu.build.abstractfactory;

public class App {
    public static void main(String[] args) {
        //产生具体工厂
        IFactory factory = new SportFactory();
        //生产特定产品
        factory.getCar();
        factory.getPack();
    }
}
