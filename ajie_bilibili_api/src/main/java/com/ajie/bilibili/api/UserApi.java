package com.ajie.bilibili.api;

import com.ajie.bilibili.domain.JsonResponse;
import com.ajie.bilibili.model.User;
import com.ajie.bilibili.service.UserService;
import com.ajie.bilibili.utils.RSAUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
@RestController
@RequestMapping("/user")
public class UserApi {

    @Resource
    private UserService userService;

    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String key = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(key);
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
     * @param user 用户表单提交的用户信息
     * @return 返回用户生成的token
     */
    @PostMapping("/login")
    public JsonResponse<String> userLogin(@RequestBody User user) {
        String token = userService.userLogin(user);
        return new JsonResponse<>(token);
    }


}
