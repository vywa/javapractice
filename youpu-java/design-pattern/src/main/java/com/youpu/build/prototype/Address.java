package com.youpu.build.prototype;

public class Address implements Cloneable {

    private String provice;
    private String city;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Address address = new Address();
        address.setProvice(this.getProvice());
        address.setCity(this.getCity());
        return address;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
