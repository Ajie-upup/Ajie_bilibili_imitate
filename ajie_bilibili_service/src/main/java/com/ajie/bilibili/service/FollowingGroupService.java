package com.ajie.bilibili.service;

import com.ajie.bilibili.model.FollowingGroup;

import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
public interface FollowingGroupService {
    FollowingGroup getByType(String type);

    FollowingGroup getById(Long groupId);

    List<FollowingGroup> getByUserId(Long userId);
}
