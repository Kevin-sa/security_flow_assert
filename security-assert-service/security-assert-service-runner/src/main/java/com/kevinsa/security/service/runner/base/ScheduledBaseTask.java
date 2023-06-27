package com.kevinsa.security.service.runner.base;

import com.sun.istack.internal.NotNull;

public interface ScheduledBaseTask {

    @NotNull
    String taskName();

    @NotNull
    String bizMsg();

    Runnable exec();

}
