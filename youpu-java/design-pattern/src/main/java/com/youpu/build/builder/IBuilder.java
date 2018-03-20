package com.youpu.build.builder;

/**
 * 抽象创造者，提供创建产品共同接口
 */
public interface IBuilder {

    //创建引擎
    void buildEngine();

    //创建车玻璃
    void buildGlass();

    //创建方向盘
    void buildSteerWheel();

    //返回创建好的产品
    ICar getCar();
}
