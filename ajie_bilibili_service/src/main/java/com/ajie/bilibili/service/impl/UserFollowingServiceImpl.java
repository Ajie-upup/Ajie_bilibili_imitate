package com.ajie.bilibili.service.impl;

import com.ajie.bilibili.constant.UserConstant;
import com.ajie.bilibili.dao.UserFollowingDao;
import com.ajie.bilibili.domain.ConditionException;
import com.ajie.bilibili.model.FollowingGroup;
import com.ajie.bilibili.model.User;
import com.ajie.bilibili.model.UserFollowing;
import com.ajie.bilibili.model.UserInfo;
import com.ajie.bilibili.service.FollowingGroupService;
import com.ajie.bilibili.service.UserFollowingService;
import com.ajie.bilibili.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@Service
public class UserFollowingServiceImpl implements UserFollowingService {
    @Resource
    private UserFollowingDao userFollowingDao;

    @Resource
    private FollowingGroupService followingGroupService;

    @Resource
    private UserService userService;

    /**
     * 添加用户关注
     *
     * @param userFollowing 用户关注信息
     */
    @Transactional
    @Override
    public void addUserFollowings(UserFollowing userFollowing) {
        Long groupId = userFollowing.getGroupId();
        //1、判断分组是否存在
        if (groupId == null) {
            FollowingGroup followingGroup = followingGroupService.getByType(UserConstant.USER_FOLLOWING_GROUP_TYPE_DEFAULT);
            userFollowing.setGroupId(followingGroup.getId());
        } else {
            FollowingGroup followingGroup = followingGroupService.getById(groupId);
            if (followingGroup == null) {
                throw new ConditionException("该分组不存在！");
            }
            userFollowing.setGroupId(followingGroup.getId());
        }
        //2、判断关注的人是否存在
        Long followingId = userFollowing.getFollowingId();
        User user = userService.getUserById(followingId);
        if (user == null) {
            throw new ConditionException("您关注的用户不存在！");
        }
        //3、先删除后添加
        userFollowingDao.deleteFollowingGroup(userFollowing.getUserId(), followingId);
        userFollowing.setCreateTime(new Date());
        userFollowingDao.addUserFollowings(userFollowing);
    }

    /**
     * 获取用户关注列表
     *
     * @param userId 当前用户id
     * @return 按照关注用户进行分组后的用户关注分组列表
     */
    @Override
    public List<FollowingGroup> getUserFollowings(Long userId) {
        //获取关注用户列表
        List<UserFollowing> list = userFollowingDao.getUserFollowings(userId);
        Set<Long> followingIdSet = list.stream().map(UserFollowing::getFollowingId).collect(Collectors.toSet());
        //根据关注列表用户id查询用户信息
        List<UserInfo> userInfoList = new ArrayList<>();
        if (followingIdSet.size() > 0) {
            userInfoList = userService.getUserInfoByUserIds(followingIdSet);
        }
        for (UserFollowing userFollowing : list) {
            for (UserInfo userInfo : userInfoList) {
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())) {
                    //将关注用户和当前用户进行匹配
                    userFollowing.setUserInfo(userInfo);
                }
            }
        }
        //将关注用户按照关注分组进行分类
        List<FollowingGroup> groupList = followingGroupService.getByUserId(userId);
        //全部关注分组
        FollowingGroup allGroup = new FollowingGroup();
        allGroup.setName(UserConstant.USER_FOLLOWING_GROUP_ALL_NAME);
        allGroup.setFollowingUserInfoList(userInfoList);
        //用户分组总分组
        List<FollowingGroup> followingGroupList = new ArrayList<>();
        followingGroupList.add(allGroup);

        for (FollowingGroup group : groupList) {
            List<UserInfo> userInfos = new ArrayList<>();
            for (UserFollowing userFollowing : list) {
                if (group.getId().equals(userFollowing.getGroupId())) {
                    userInfos.add(userFollowing.getUserInfo());
                }
            }
            group.setFollowingUserInfoList(userInfos);
            followingGroupList.add(group);
        }
        return followingGroupList;
    }

    /**
     * 获取当前用户粉丝列表
     *
     * @param userId 当前用户Id
     * @return
     */
    @Override
    public List<UserFollowing> getUserFans(Long userId) {
        //获取粉丝列表   --  当前userId匹配的followingId
        List<UserFollowing> fanList = userFollowingDao.getUserFans(userId);
        Set<Long> fanIdSet = fanList.stream().map(UserFollowing::getUserId).collect(Collectors.toSet());
        //根据列表查询用户基本信息
        List<UserInfo> userInfoList = new ArrayList<>();
        if (fanIdSet.size() > 0) {
            userInfoList = userService.getUserInfoByUserIds(fanIdSet);
        }
        List<UserFollowing> followingList = userFollowingDao.getUserFollowings(userId);

        for (UserFollowing fan : fanList) {
            for (UserInfo userinfo : userInfoList) {
                if (fan.getUserId().equals(userinfo.getUserId())) {
                    userinfo.setFollowed(false);
                    fan.setUserInfo(userinfo);
                }
            }
            for (UserFollowing following : followingList) {
                if (following.getUserId().equals(fan.getUserId())) {
                    fan.getUserInfo().setFollowed(true);
                }
            }
        }
        return fanList;
    }

    @Override
    public Long addUserFollowingGroups(FollowingGroup followingGroup) {
        followingGroup.setCreateTime(new Date());
        followingGroup.setType(UserConstant.USER_FOLLOWING_GROUP_TYPE_USER);
        followingGroupService.addUserFollowingGroups(followingGroup);

        return followingGroup.getId();
    }

    @Override
    public List<FollowingGroup> getUserFollowingGroups(Long userId) {
        return followingGroupService.getUserFollowingGroups(userId);
    }

    @Override
    public List<UserInfo> checkFollowingStatus(List<UserInfo> userInfoList, Long userId) {
        List<UserFollowing> userFollowingList = userFollowingDao.getUserFollowings(userId);
        for(UserInfo userInfo : userInfoList){
            userInfo.setFollowed(false);
            for(UserFollowing userFollowing : userFollowingList){
                if(userFollowing.getFollowingId().equals(userInfo.getUserId())){
                    userInfo.setFollowed(true);
                }
            }
        }
        return userInfoList;
    }


}
