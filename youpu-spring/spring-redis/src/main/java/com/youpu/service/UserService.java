package com.youpu.service;

import com.youpu.domain.User;

public interface UserService {

    public User findByName(String username);

    public void updateUser(User user);
}
