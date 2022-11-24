package com.ajie.bilibili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Author: ajie
 * @Date: 2022/11/23
 */
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MyApplication.class, args);
    }
}
