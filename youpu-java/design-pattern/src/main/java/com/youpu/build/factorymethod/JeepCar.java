package com.youpu.build.factorymethod;

public class JeepCar implements ICar{
    @Override
    public void drive() {
        System.out.println("开吉普车");
    }
}
