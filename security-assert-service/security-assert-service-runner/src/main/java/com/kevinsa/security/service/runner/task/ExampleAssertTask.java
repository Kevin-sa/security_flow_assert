package com.kevinsa.security.service.runner.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import com.kevinsa.security.service.runner.base.ScheduledBaseTask;
import com.kevinsa.security.service.service.assertCheck.AssertExecutorFactory;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;


@Service
public class ExampleAssertTask implements ScheduledBaseTask {

    private final AssertExecutorFactory assertExecutorFactory;

    private final AssertStepAction assertStepAction;

    @Autowired
    public ExampleAssertTask(AssertExecutorFactory assertExecutorFactory, AssertStepAction assertStepAction) {
        this.assertExecutorFactory = assertExecutorFactory;
        this.assertStepAction = assertStepAction;
    }


    public String taskName() {
        return ExampleAssertTask.class.getName();
    }


    public String bizMsg() {
        return "kwai-shop";
    }

    @Override
    public Integer initialDelay() {
        return 10;
    }

    @Override
    public Integer period() {
        return 24;
    }

    public Runnable exec() {
        return () -> {
            try {
                AssertProcessContext assertProcessContext = assertExecutorFactory.createDefaultContext();
                assertStepAction.process(assertProcessContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        exec();
        System.out.println("example assert task");
    }
}
