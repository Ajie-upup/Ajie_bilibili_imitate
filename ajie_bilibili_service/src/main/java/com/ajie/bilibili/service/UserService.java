package com.ajie.bilibili.service;

import com.ajie.bilibili.domain.PageResult;
import com.ajie.bilibili.model.User;
import com.ajie.bilibili.model.UserInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
public interface UserService {
    void userRegister(User user);

    User getUserByPhone(String phone);

    String userLogin(User user) throws Exception;

    User getUserInfo(Long userId);

    void updateUsers(User user) throws Exception;

    void updateUserInfos(UserInfo userInfo);

    User getUserById(Long id);

    List<UserInfo> getUserInfoByUserIds(Set<Long> userIdList);

    PageResult<UserInfo> pageListUserInfos(JSONObject params);
}
