package com.ajie.bilibili.api;

import com.ajie.bilibili.service.UserFollowingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@RestController
@RequestMapping("/userFollow")
public class UserFollowingApi {

    @Resource
    private UserFollowingService userFollowingService;




}
