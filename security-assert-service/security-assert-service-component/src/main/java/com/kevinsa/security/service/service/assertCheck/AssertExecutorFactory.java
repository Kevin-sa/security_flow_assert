package com.kevinsa.security.service.service.assertCheck;


import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.assertCheck.flowConfig.DefaultFlowConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AssertExecutorFactory {

    public DefaultProcessContext createDefaultContext() {
        DefaultProcessContext context = new DefaultProcessContext();
        context.setBizId(DefaultFlowConfig.DEFAULT_ASSERT_EXECUTOR_FLOW);
        return context;
    }
}
