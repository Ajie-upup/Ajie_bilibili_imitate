package com.ajie.bilibili.service;

import com.ajie.bilibili.model.FollowingGroup;
import com.ajie.bilibili.model.UserFollowing;
import com.ajie.bilibili.model.UserInfo;

import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
public interface UserFollowingService {

    void addUserFollowings(UserFollowing userFollowing);

    List<FollowingGroup> getUserFollowings(Long currentUserId);

    List<UserFollowing> getUserFans(Long currentUserId);

    Long addUserFollowingGroups(FollowingGroup followingGroup);

    List<FollowingGroup> getUserFollowingGroups(Long userId);

    List<UserInfo> checkFollowingStatus(List<UserInfo> list, Long userId);
}
