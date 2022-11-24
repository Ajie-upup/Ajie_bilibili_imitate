package com.ajie.bilibili.controller;

import com.ajie.bilibili.service.impl.DemoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ajie
 * @Date: 2022/11/23
 */
@RestController
public class demoController {
    @Resource
    private DemoServiceImpl demoService;

    @GetMapping("/query")
    public Long query(@RequestParam Long id) {
        return demoService.query(id);
    }
}
