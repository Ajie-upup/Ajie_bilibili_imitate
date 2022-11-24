package com.ajie.bilibili.service.impl;

import com.ajie.bilibili.dao.DemoDao;
import com.ajie.bilibili.service.DenoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/23
 */
@Service
public class DemoServiceImpl implements DenoService {
    @Resource
    private DemoDao demoDao;

    @Override
    public Long query(Long id) {
        return demoDao.query(id);
    }
}
