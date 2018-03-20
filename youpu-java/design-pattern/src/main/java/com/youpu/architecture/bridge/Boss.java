package com.youpu.architecture.bridge;

public class Boss {
    ILeader leader;

    public ILeader getLeader() {
        return leader;
    }

    public void setLeader(ILeader leader) {
        this.leader = leader;
    }

    public void action(){
        leader.action();
    }
}
