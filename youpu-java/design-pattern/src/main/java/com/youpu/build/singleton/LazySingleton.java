package com.youpu.build.singleton;

public class LazySingleton {

    private LazySingleton(){}

    private static LazySingleton singleton=null;

    //双重检测
    public static LazySingleton getSingleton(){
        if(singleton == null){
            synchronized (LazySingleton.class){
                if(singleton == null){
                    System.out.println("第一次实例化对象");
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }
}
