package com.youpu.build.prototype;

import java.util.Date;

public class Resume implements Cloneable{
    private String username;
    private Date birthday;
    private String school;
    private Address address;

//    public Resume(Address address) {
//        try {
//            this.address = (Address) address.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Resume resume = new Resume();
        resume.setUsername(this.username);
        resume.setBirthday(this.birthday);
        resume.setSchool(this.school);
        resume.setAddress(this.address);
        return resume;
    }

//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    public void display(){
        System.out.println("姓名:"+username);
        System.out.println("生日:"+birthday);
        System.out.println("学校"+school);
        System.out.println("省份"+address.getProvice());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
