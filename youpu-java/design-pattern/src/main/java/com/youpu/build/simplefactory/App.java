package com.youpu.build.simplefactory;

public class App {


    public static void main(String[] args) {
        try {
            ICar iCar = CarFactory.getCar(CarType.SPORT);
            iCar.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
