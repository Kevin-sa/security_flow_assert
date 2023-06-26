package com.kevinsa.security.service.service.assertCheck.base;

public interface AssertStepAction<T extends AssertProcessContext> {

    void process(T processContext);
}
