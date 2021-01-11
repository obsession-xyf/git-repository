package com.itheima.health.jobs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/11 9:36 上午
 */
public class JobApplication {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-jobs.xml");
        // 阻塞
        System.in.read();
    }
}
