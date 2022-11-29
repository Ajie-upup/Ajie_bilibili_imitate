package com.ajie.bilibili.dao;

import com.ajie.bilibili.model.FollowingGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@Mapper
public interface FollowingGroupDao {
    FollowingGroup getByType(String type);

    FollowingGroup getById(Long id);

    List<FollowingGroup> getByUserId(Long userId);

    Integer addUserFollowingGroups(FollowingGroup followingGroups);

    List<FollowingGroup> getUserFollowingGroups(Long userId);
}
