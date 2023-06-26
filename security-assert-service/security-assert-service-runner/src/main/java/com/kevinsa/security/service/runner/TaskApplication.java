package com.kevinsa.security.service.runner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kevinsa.security.service.dao.mapper")
@SpringBootApplication()
public class TaskApplication {
}
