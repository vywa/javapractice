package com.youpu.architecture.decorator;

public class DecoratorCar2 implements ICar{
    private ICar car;

    public DecoratorCar2(ICar car) {
        this.car = car;
    }

    public void sayyou(){
        System.out.println("第二个装饰漆");
    }

    @Override
    public void run() {
        this.sayyou();
        car.run();
    }
}
