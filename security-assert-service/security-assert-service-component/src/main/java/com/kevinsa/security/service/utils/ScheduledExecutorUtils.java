package com.kevinsa.security.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class ScheduledExecutorUtils {

    private static final int CORE_POOL_SIZE = 10;

    private ScheduledExecutorService executorService;

    @Autowired
    private ScheduledExecutorUtils() {
    }

    @PostConstruct
    private void init() {
        executorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    }

    public ScheduledExecutorService getInstance() {
        return executorService;
    }
}
