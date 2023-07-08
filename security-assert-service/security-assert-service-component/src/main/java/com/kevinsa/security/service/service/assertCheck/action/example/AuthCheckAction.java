package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;

public class AuthCheckAction implements AssertStepAction<DefaultProcessContext> {


    /**
     * 1、做鉴权位有效性校验；
     * 2、鉴权值失效获取新的鉴权值并做替换
     */
    @Override
    public void process(DefaultProcessContext processContext) {

    }

    private void authExpireCheck() {

    }

    private void getCsrfToken() {

    }

    private void getAuthToken() {

    }
}
