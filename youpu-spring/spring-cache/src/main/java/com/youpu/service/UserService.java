package com.youpu.service;

import com.youpu.domain.User;

public interface UserService {

    public User getUser(User user);
    public int addUser(User user);
    public int delUser(User user);
}
