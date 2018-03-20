package com.youpu.architecture.decorator;

public class DecoratorCar implements ICar{

    private Car car;

    public DecoratorCar(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        this.addMe();
        car.run();
    }

    private void addMe() {
        System.out.println("装饰器方法");
    }
}
