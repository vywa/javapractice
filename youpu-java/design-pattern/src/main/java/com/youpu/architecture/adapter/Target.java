package com.youpu.architecture.adapter;

public class Target implements ITarget{

    private Origin origin;

    @Override
    public void newDeal(int type) {
        if(type==1){
            origin.deal();
        }else{
            System.out.println("新的方法");
        }
    }
}
