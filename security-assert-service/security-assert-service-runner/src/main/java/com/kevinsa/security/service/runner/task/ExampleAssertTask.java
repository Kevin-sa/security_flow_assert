package com.kevinsa.security.service.runner.task;


import com.kevinsa.security.service.service.task.ExampleAssertTaskServer;
import com.kevinsa.security.service.utils.ScheduledExecutorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import com.kevinsa.security.service.runner.base.ScheduledBaseTask;

@Service
public class ExampleAssertTask implements ScheduledBaseTask {

    private final ExampleAssertTaskServer exampleAssertTaskServer;

    private final ScheduledExecutorUtils scheduledExecutorUtils;

    @Autowired
    public ExampleAssertTask(ExampleAssertTaskServer exampleAssertTaskServer,
                             ScheduledExecutorUtils scheduledExecutorUtils) {
        this.exampleAssertTaskServer = exampleAssertTaskServer;
        this.scheduledExecutorUtils = scheduledExecutorUtils;
    }


    public String taskName() {
        return ExampleAssertTask.class.getName();
    }


    public String bizMsg() {
        return "kwai-shop";
    }

    @Override
    public Integer initialDelayHour() {
        return 10;
    }

    @Override
    public Integer periodHour() {
        return 24;
    }

    public Runnable exec() {
        return exampleAssertTaskServer.getRunnableExec(bizMsg());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("this is ExampleAssertTask");
//        System.out.println(System.currentTimeMillis());
//        scheduledExecutorUtils.getInstance().scheduleAtFixedRate(exec(),
//                scheduledExecutorUtils.getInitialDelay(initialDelayHour(), 0, periodHour()),
//                periodHour() * 60 * 60,
//                TimeUnit.SECONDS);
    }
}
