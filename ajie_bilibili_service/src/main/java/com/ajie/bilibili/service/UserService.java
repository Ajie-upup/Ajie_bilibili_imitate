package com.ajie.bilibili.service;

import com.ajie.bilibili.model.User;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
public interface UserService {
    void userRegister(User user);

    User getUserByPhone(String phone);

    String userLogin(User user);
}
