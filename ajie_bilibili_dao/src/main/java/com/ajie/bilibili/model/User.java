package com.ajie.bilibili.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
@Data
public class User {
    private Long id;
    private String phone;
    private String email;
    private String password;
    private String salt;
    private Date createTime;
    private Date updateTime;
}
