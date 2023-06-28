package com.kevinsa.security.service.runner;

import com.kevinsa.security.service.runner.task.ExampleAssertTask;
import com.kevinsa.security.service.service.assertCheck.ClassB;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@MapperScan("com.kevinsa.security.service.dao.mapper")
@ComponentScan("com.kevinsa.security.service")
@SpringBootApplication()
@EnableWebMvc
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    private static int calculateInitialDelay(int targetHour) {
        LocalTime now = LocalTime.now();
        int currentHour = now.getHour();
        int delay = targetHour - currentHour;

        if (delay < 0) {
            delay += 24;
        }
        return delay;
    }
}
