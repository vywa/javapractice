package com.youpu.build.abstractfactory;

public class SportFactory implements IFactory{
    @Override
    public ICar getCar() {
        System.out.println("生产sport汽车");
        return new SportCar();
    }

    @Override
    public IBackPack getPack() {
        System.out.println("生产sport背包");
        return new SportBackPack();
    }
}
