package com.youpu.architecture.decorator;

public class App {
    public static void main(String[] args) {
        Car car = new Car();
        DecoratorCar decoratorCar = new DecoratorCar(car);

        DecoratorCar2 decoratorCar2 = new DecoratorCar2(decoratorCar);
        decoratorCar2.run();
    }
}
