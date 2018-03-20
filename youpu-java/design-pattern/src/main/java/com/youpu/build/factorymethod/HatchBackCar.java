package com.youpu.build.factorymethod;

public class HatchBackCar implements ICar{
    @Override
    public void drive() {
        System.out.println("开两厢车");
    }
}
