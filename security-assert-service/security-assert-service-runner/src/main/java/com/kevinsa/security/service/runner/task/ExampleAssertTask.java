package com.kevinsa.security.service.runner.task;

import com.kevinsa.security.service.runner.base.ScheduledBaseTask;



public class ExampleAssertTask implements ScheduledBaseTask {
    public String taskName() {
        return ExampleAssertTask.class.getName();
    }

    public String bizMsg() {
        return "kwai-shop";
    }

    public Runnable exec() {
        return () -> {
            System.out.println("定时任务执行：" + System.currentTimeMillis());
        };
    }
}
