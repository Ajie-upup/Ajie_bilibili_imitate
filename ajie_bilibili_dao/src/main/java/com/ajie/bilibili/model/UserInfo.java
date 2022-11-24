package com.ajie.bilibili.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
@Data
public class UserInfo {
    private Long id;
    private Long userId;
    private String nick;
    private String avatar;
    private String sign;
    private String gender;
    private String birth;
    private Date createTime;
    private Date updateTime;
}
