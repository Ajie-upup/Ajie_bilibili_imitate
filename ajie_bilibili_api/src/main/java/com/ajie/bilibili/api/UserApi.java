package com.ajie.bilibili.api;

import com.ajie.bilibili.api.support.UserSupport;
import com.ajie.bilibili.domain.JsonResponse;
import com.ajie.bilibili.domain.PageResult;
import com.ajie.bilibili.model.User;
import com.ajie.bilibili.model.UserInfo;
import com.ajie.bilibili.service.UserFollowingService;
import com.ajie.bilibili.service.UserService;
import com.ajie.bilibili.utils.RSAUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
@RestController
@RequestMapping("/user")
public class UserApi {

    @Resource
    private UserService userService;

    @Resource
    private UserSupport userSupport;

    @Resource
    private UserFollowingService userFollowingService;

    /**
     * 获取rsa公钥
     *
     * @return 通过工具类返回rsa公钥
     */
    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String key = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(key);
    }

    /**
     * 获取请求头token中的用户信息
     *
     * @return 用户信息
     */
    @GetMapping()
    public JsonResponse<User> getUserInfo() {
        Long userId = userSupport.getCurrentUserId();
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }

    /**
     * 用户注册接口
     *
     * @param user 前端传递用户基本信息
     * @return 返回用户添加成功，失败则抛出异常
     */
    @PostMapping("/register")
    public JsonResponse<String> userRegister(@RequestBody User user) {
        userService.userRegister(user);
        return JsonResponse.success();
    }

    /**
     * 用户登录
     *
     * @param user 用户表单提交的用户信息
     * @return 返回用户生成的token
     */
    @PostMapping("/login")
    public JsonResponse<String> userLogin(@RequestBody User user) throws Exception {
        String token = userService.userLogin(user);
        return new JsonResponse<>(token);
    }

    /**
     * 获取token用户信息，更新数据库信息
     *
     * @param user token中用户信息
     * @return 修改成功
     */
    @PutMapping("/updateUsers")
    public JsonResponse<String> updateUsers(@RequestBody User user) throws Exception {
        Long userId = userSupport.getCurrentUserId();
        user.setId(userId);
        userService.updateUsers(user);
        return JsonResponse.success();
    }

    /**
     * 更新用户基本信息
     * @param userInfo 用户基本信息
     * @return
     * @throws Exception
     */
    @PutMapping("/updateUserInfos")
    public JsonResponse<String> updateUserInfos(@RequestBody UserInfo userInfo) throws Exception {
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }

    /**
     * 获取用户信息分页查询
     * @param no 当前页面num
     * @param size 页面大小
     * @param nick 用户昵称
     * @return
     */
    @GetMapping("/getUserInfos")
    public JsonResponse<PageResult<UserInfo>> getUserInfos(@RequestParam Integer no, @RequestParam Integer size, String nick) {
        Long userId = userSupport.getCurrentUserId();
        JSONObject params = new JSONObject();
        params.put("no", no);
        params.put("size", size);
        params.put("nick", nick);
        params.put("userId", userId);
        PageResult<UserInfo> result = userService.pageListUserInfos(params);
        if (result.getTotal() > 0) {
            List<UserInfo> checkedUserInfoList = userFollowingService.checkFollowingStatus(result.getList(), userId);
            result.setList(checkedUserInfoList);
        }
        return new JsonResponse<>(result);
    }


}
