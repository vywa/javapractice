package com.youpu.build.simplefactory;

public class CarFactory {

    public static ICar getCar(CarType carType) throws Exception{
        switch (carType){
            case SPORT:
                return new SportCar();
            case JEEP:
                return new JeepCar();
            case HATCH_BACK:
                return new HatchBackCar();
            default:
                throw new Exception("没有改种车型");

        }
    }
}
