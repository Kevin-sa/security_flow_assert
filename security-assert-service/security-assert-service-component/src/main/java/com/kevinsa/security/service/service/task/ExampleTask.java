package com.kevinsa.security.service.service.task;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.kevinsa.security.service.service.assertCheck.AssertExecutorFactory;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessFlow;

public class ExampleTask {

    @Autowired
    private AssertExecutorFactory assertExecutorFactory;

//    @Resource
//    private AssertProcessFlow processFlow;

    public void executor() throws Exception {
        AssertProcessContext context = assertExecutorFactory.createDefaultContext();
//        processFlow.process(context);
    }
}
