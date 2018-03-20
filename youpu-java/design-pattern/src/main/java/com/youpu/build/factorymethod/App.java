package com.youpu.build.factorymethod;

public class App {
    public static void main(String[] args) {
        IFactory factory = new JeepFactory();
        factory.createCar().drive();
    }
}
