package com.youpu.build.singleton;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        App app = new App();
        app.hungarySingleton();
//        app.lazySingleton();
//        app.intterclass();
    }

    public void intterclass(){
        List<StaticInnerSingleton> list = new ArrayList<StaticInnerSingleton>();
        for(int i=0;i<10;i++){
            list.add(StaticInnerSingleton.getSingleton());
        }
        for(StaticInnerSingleton temp : list){
            System.out.println(temp.hashCode());
        }

    }

    public void lazySingleton(){
        List<LazySingleton> list = new ArrayList<LazySingleton>();
        for(int i=0;i<10;i++){
            list.add(LazySingleton.getSingleton());
        }
        for(LazySingleton temp : list){
            System.out.println(temp.hashCode());
        }
    }

    public void hungarySingleton(){
        List<HungrySingleton> list = new ArrayList<HungrySingleton>();
        for(int i=0;i<10;i++){
            list.add(HungrySingleton.getHungrySingleton());
        }

        for(HungrySingleton temp:list){
            temp.say();

            System.out.println(temp.hashCode());
        }
    }
}
