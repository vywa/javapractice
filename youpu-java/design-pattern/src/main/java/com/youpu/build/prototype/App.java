package com.youpu.build.prototype;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        Address address = new Address();
        address.setProvice("陕西");
        Resume resume = new Resume();
        resume.setBirthday(new Date());
        resume.setUsername("潘安");
        resume.setSchool("摸弄");
        resume.setAddress(address);


        resume.display();

        try {
            Resume resumeb = (Resume) resume.clone();
            String result = resume==resumeb?"相等":"不等";
            System.out.println("两个实体 "+result);
            Address address1 = resume.getAddress();
            address1.setProvice("北京");
            resume.setAddress(address1);
            resumeb.display();
            resume.display();


        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
