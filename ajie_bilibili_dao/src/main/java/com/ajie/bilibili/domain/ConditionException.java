package com.ajie.bilibili.domain;

import lombok.Data;

/**
 * @Author: ajie
 * @Date: 2022/11/24
 */
@Data
public class ConditionException extends RuntimeException {
    public static final long serialVersionUID = 1L;
    private String code;

    public ConditionException(String code, String name) {
        super(name);
        this.code = code;
    }

    public ConditionException(String name) {
        super(name);
        code = "500";
    }
}
