package com.youpu.build.builder;

public class Director {
    private IBuilder builder;

    public Director(IBuilder builder) {
        this.builder = builder;
    }

    public ICar build(){
        builder.buildEngine();
        builder.buildGlass();
        builder.buildSteerWheel();

        return builder.getCar();
    }
}
