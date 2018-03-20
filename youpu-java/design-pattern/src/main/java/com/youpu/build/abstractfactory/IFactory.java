package com.youpu.build.abstractfactory;

/**
 * 抽象工厂类
 */
public  interface IFactory {

    /**
     * 抽象方法，创建车
     * @return
     */
    public ICar getCar();

    /**
     * 创建背包
     * @return
     */
    public IBackPack getPack();

}
