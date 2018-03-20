package com.youpu.build.singleton;

public class StaticInnerSingleton {
    private StaticInnerSingleton(){}

    private static class InnerObject{
      private static StaticInnerSingleton singleton = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getSingleton(){
        return InnerObject.singleton;
    }
}
