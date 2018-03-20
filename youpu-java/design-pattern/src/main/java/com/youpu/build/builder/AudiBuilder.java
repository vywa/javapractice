package com.youpu.build.builder;

public class AudiBuilder implements IBuilder{

    Audi audi = new Audi();

    @Override
    public void buildEngine() {
        audi.engine = "AudiEngine";
    }

    @Override
    public void buildGlass() {
        audi.glass = 3.5;
    }

    @Override
    public void buildSteerWheel() {
        audi.steerWheel = "AudiSteerWheel";
    }

    @Override
    public ICar getCar() {
        return audi;
    }
}
