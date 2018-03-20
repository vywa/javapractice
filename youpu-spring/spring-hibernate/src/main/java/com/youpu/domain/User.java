package com.youpu.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements Serializable{

    private long id;
    private String username;
    private String password;

    private Set<Address> address = new HashSet<Address>();


}
