package com.kevinsa.security.service.runner.base;

import org.springframework.boot.ApplicationRunner;

import com.sun.istack.internal.NotNull;

public interface ScheduledBaseTask extends ApplicationRunner {

    @NotNull
    String taskName();

    @NotNull
    String bizMsg();

    @NotNull
    Integer initialDelayHour();

    @NotNull
    Integer periodHour();

    Runnable exec();

}
