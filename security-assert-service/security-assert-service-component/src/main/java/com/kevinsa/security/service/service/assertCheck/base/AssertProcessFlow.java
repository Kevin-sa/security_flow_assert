package com.kevinsa.security.service.service.assertCheck.base;

import java.util.HashMap;
import java.util.Map;

public class AssertProcessFlow {

    private Map<String, ProcessTemplate> templateConfig;

    AssertProcessFlow() {
        templateConfig = new HashMap<>();
    }

    public <T extends AssertProcessContext> void process(T context) throws Exception {
        String bizMsg = context.getBizMsg();
        ProcessTemplate processTemplate = templateConfig.get(bizMsg);

        if (processTemplate == null) {
            throw new Exception("processTemplate not exist");
        }

        for (AssertStepAction action : processTemplate.getProcessConfig()) {
            action.process(context);
            if (context.getIsBreak()) {
                break;
            }
        }
    }

    public Map<String, ProcessTemplate> getTemplateConfig() {
        return templateConfig;
    }

    public void setTemplateConfig(Map<String, ProcessTemplate> templateConfig) {
        this.templateConfig = templateConfig;
    }
}
