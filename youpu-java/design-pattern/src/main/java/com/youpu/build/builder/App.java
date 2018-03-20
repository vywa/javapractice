package com.youpu.build.builder;

public class App {

    public static void main(String[] args) {
        AudiBuilder audiBuilder = new AudiBuilder();
        Director director = new Director(audiBuilder);

        ICar car = director.build();

        car.driver();
    }
}
