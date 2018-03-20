package com.youpu.build.factorymethod;

public class JeepFactory implements IFactory{
    @Override
    public ICar createCar() {
        return new JeepCar();
    }
}
