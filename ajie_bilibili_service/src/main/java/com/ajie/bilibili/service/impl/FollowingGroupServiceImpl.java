package com.ajie.bilibili.service.impl;

import com.ajie.bilibili.dao.FollowingGroupDao;
import com.ajie.bilibili.model.FollowingGroup;
import com.ajie.bilibili.service.FollowingGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@Service
public class FollowingGroupServiceImpl implements FollowingGroupService {

    @Resource
    private FollowingGroupDao followingGroupDao;

    @Override
    public FollowingGroup getByType(String type) {
        return followingGroupDao.getByType(type);
    }

    @Override
    public FollowingGroup getById(Long id) {
        return followingGroupDao.getById(id);
    }

    @Override
    public List<FollowingGroup> getByUserId(Long userId) {
        return followingGroupDao.getByUserId(userId);
    }

}
