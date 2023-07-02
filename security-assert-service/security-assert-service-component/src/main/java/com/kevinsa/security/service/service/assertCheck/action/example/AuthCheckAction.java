package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;

public class AuthCheckAction implements AssertStepAction<AssertProcessContext> {

    @Override
    public void process(AssertProcessContext processContext) {
        System.out.println("path:" + processContext.getFlowData().getApiPath());
    }
}
