package com.youpu.build.simplefactory;

public enum CarType {

    SPORT("跑车",1),JEEP("越野车",2),HATCH_BACK("两厢车",3);

    private String name;
    private int index;

    public static String getName(int index){
        for(CarType carType:CarType.values()){
            if(carType.getIndex()==index){
                return carType.getName();
            }
        }
        return null;
    }

    CarType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
