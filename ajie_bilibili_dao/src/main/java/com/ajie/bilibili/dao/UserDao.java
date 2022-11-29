package com.ajie.bilibili.dao;

import com.ajie.bilibili.model.User;
import com.ajie.bilibili.model.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Integer updateUsers(User user);

    Integer updateUserInfos(UserInfo userInfo);

    List<UserInfo> getUserInfoByUserIds(Set<Long> userIdList);

    Integer pageCountUserInfos(Map<String, Object> params);

    List<UserInfo> pageListUserInfos(JSONObject params);
}
