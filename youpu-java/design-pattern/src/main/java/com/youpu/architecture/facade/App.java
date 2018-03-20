package com.youpu.architecture.facade;

public class App {
    public static void main(String[] args) {
        Leader leader = new Leader();
        leader.repaire();

        boolean isOk = leader.check();
    }
}
