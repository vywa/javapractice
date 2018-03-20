package com.youpu.architecture.bridge;

public class LeaderA implements ILeader{
    @Override
    public void action() {
        System.out.println("领导1做事");
    }
}
