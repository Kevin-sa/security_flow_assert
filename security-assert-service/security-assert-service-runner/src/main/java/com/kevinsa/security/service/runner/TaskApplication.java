package com.kevinsa.security.service.runner;

import com.kevinsa.security.service.runner.task.ExampleAssertTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@MapperScan("com.kevinsa.security.service.dao.mapper")
@SpringBootApplication()
public class TaskApplication {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        ExampleAssertTask exampleAssertTask = new ExampleAssertTask();
        // 注册任务，延迟 1 秒后执行，然后每隔 5 秒重复执行一次
        executorService.scheduleAtFixedRate(exampleAssertTask.exec(), 1000, 5000, TimeUnit.MILLISECONDS);
    }
}
