package com.youpu.build.singleton;

public class HungrySingleton {
    private HungrySingleton(){}

    private static HungrySingleton  hungrySingleton = null;

    static{
        hungrySingleton = new HungrySingleton();
    }

//    private static HungrySingleton hungrySingleton = new HungrySingleton();

    public void say(){
        System.out.println("我是饿汉式单例模式");
    }

    //静态工厂方法
    public static HungrySingleton getHungrySingleton(){
        return hungrySingleton;
    }
}
