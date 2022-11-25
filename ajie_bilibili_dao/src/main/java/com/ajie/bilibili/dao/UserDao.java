package com.ajie.bilibili.dao;

import com.ajie.bilibili.model.User;
import com.ajie.bilibili.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
@Mapper
public interface UserDao {

    User getUserByPhone(String phone);


    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserById(Long id);

    UserInfo getUserInfoByUserId(Long userId);
}
