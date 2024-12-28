package com.zeeyeh.discio.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Leon_Keiran
 * @description 项目入口
 * @date 2024/12/24/周二 20:39:06
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@ComponentScan("com.zeeyeh.discio")
@SpringBootApplication(scanBasePackages = "com.zeeyeh.discio")
@MapperScan("com.zeeyeh.discio.system.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
