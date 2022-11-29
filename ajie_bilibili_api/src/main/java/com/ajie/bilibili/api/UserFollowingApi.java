package com.ajie.bilibili.api;

import com.ajie.bilibili.api.support.UserSupport;
import com.ajie.bilibili.domain.JsonResponse;
import com.ajie.bilibili.model.FollowingGroup;
import com.ajie.bilibili.model.UserFollowing;
import com.ajie.bilibili.service.UserFollowingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/26
 */
@RestController
@RequestMapping("/userFollow")
public class UserFollowingApi {

    @Resource
    private UserFollowingService userFollowingService;

    @Resource
    private UserSupport userSupport;

    /**
     * 添加用户关注
     *
     * @param userFollowing 用户关注信息
     */
    @PostMapping("/addUserFollowings")
    public JsonResponse<String> addUserFollowings(@RequestBody UserFollowing userFollowing) {
        Long currentUserId = userSupport.getCurrentUserId();
        userFollowing.setUserId(currentUserId);
        userFollowingService.addUserFollowings(userFollowing);
        return JsonResponse.success();
    }

    /**
     * 获取用户关注列表
     *
     * @return 按照关注用户进行分组后的用户关注分组列表
     */
    @GetMapping("/getUserFollowings")
    public JsonResponse<List<FollowingGroup>> getUserFollowings() {
        Long currentUserId = userSupport.getCurrentUserId();
        List<FollowingGroup> followingGroupList = userFollowingService.getUserFollowings(currentUserId);
        return new JsonResponse<>(followingGroupList);
    }

    @GetMapping("/getUserFans")
    public JsonResponse<List<UserFollowing>> getUserFans() {
        Long currentUserId = userSupport.getCurrentUserId();
        List<UserFollowing> userFans = userFollowingService.getUserFans(currentUserId);
        return new JsonResponse<>(userFans);
    }

}
