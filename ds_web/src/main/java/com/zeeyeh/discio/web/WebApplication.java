package com.zeeyeh.discio.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leon_Keiran
 * @description 项目入口
 * @date 2024/12/24/周二 20:39:06
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@SpringBootApplication(scanBasePackages = "com.zeeyeh.discio")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
