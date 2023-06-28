package com.kevinsa.security.service.runner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.kevinsa.security.service.dao.mapper")
@ComponentScan("com.kevinsa.security.service")
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
