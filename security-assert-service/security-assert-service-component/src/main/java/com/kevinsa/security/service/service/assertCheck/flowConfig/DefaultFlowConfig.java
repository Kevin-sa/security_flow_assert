package com.kevinsa.security.service.service.assertCheck.flowConfig;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.kevinsa.security.service.service.assertCheck.action.example.AuthCheckAction;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessFlow;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.base.ProcessTemplate;

@Configuration
public class DefaultFlowConfig {

    @Resource
    private AssertProcessFlow processFlow;

    public static final String DEFAULT_ASSERT_EXECUTOR_FLOW = "default_assert_executor_flow";

    @PostConstruct
    public void processFlow() {
        Map<String, ProcessTemplate> templateConfig = processFlow.getTemplateConfig();
        templateConfig.put(DEFAULT_ASSERT_EXECUTOR_FLOW, defaultAssertExecutor());
        processFlow.setTemplateConfig(templateConfig);
    }

    @Bean
    public ProcessTemplate defaultAssertExecutor() {
        ProcessTemplate template = new ProcessTemplate();

        List<AssertStepAction> processConfig = Lists.newArrayList();
        processConfig.add(authCheckAction());

        template.setProcessConfig(processConfig);
        return template;
    }

    @Bean
    AuthCheckAction authCheckAction() {return new AuthCheckAction();}

}
