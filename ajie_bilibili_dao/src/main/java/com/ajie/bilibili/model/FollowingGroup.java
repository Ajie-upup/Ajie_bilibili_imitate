package com.ajie.bilibili.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@Data
public class FollowingGroup {

    private Long id;
    private Long userId;
    private String name;
    private String type;

    private List<UserInfo> followingUserInfoList;

    private Date createTime;
    private Date updateTime;

}
