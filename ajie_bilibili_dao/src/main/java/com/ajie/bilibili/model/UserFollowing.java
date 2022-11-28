package com.ajie.bilibili.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@Data
public class UserFollowing {
    private Long id;
    private Long userId;
    private Long followingId;
    private Long groupId;
    private Date createTime;

    private UserInfo userInfo;
}
