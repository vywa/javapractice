package com.youpu.architecture.bridge;

public class App {
    public static void main(String[] args) {
        Boss boss = new Boss();
        LeaderA leaderA = new LeaderA();
        LeaderB leaderB = new LeaderB();

        boss.setLeader(leaderB);
        boss.action();
    }
}
