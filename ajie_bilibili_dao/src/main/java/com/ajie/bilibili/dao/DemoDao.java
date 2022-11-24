package com.ajie.bilibili.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ajie
 * @Date: 2022/11/23
 */
@Mapper
public interface DemoDao {
    public Long query(Long id);
}
