package com.kevinsa.security.service.service.assertCheck;


import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;
import com.kevinsa.security.service.service.assertCheck.flowConfig.DefaultConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AssertExecutorFactory {


    public AssertProcessContext createDefaultContext() {
        AssertProcessContext context = new AssertProcessContext();
        context.setBizId(DefaultConfig.DEFAULT_ASSERT_EXECUTOR_FLOW);
        return context;
    }
}
