package com.ajie.bilibili.dao;

import com.ajie.bilibili.model.UserFollowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@Mapper
public interface UserFollowingDao {
    Integer deleteFollowingGroup(@Param("userId") Long userId, @Param("followingId") Long followingId);

    Integer addUserFollowings(UserFollowing userFollowing);

    List<UserFollowing> getUserFollowings(Long userId);
}
