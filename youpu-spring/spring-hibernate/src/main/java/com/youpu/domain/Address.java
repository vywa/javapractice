package com.youpu.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable{

    private long id;
    private String province;
    private String city;

    private User user;
}
