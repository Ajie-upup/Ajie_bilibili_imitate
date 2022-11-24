package com.ajie.bilibili.domain;

import lombok.Data;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 * JSON返回数据类
 */
@Data
public class JsonResponse<T> {

    private String code;
    private String msg;
    private T data;

    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data) {
        this.data = data;
        code = "0";
        msg = "success";
    }

    public static JsonResponse<String> success() {
        return new JsonResponse<>(null);
    }

    public static JsonResponse<String> success(String data) {
        return new JsonResponse<>(data);
    }

    public static JsonResponse<String> fail() {
        return new JsonResponse<>("1", "fail");
    }

    public static JsonResponse<String> fail(String code, String msg) {
        return new JsonResponse<>(code, msg);
    }
}
