package com.kevinsa.security.service.service.collect.base;

public abstract class FilterActionUnitTemplate<T> implements FilterActionUnit<T> {

    @Override
    public ProcessContext<T> execute(ProcessContext<T> processContext) {
        try {
            paramCheck(processContext);
            contextTypeCheck(processContext);
            apiPathCheck(processContext);
            headersCheck(processContext);
            return processContext;
        } catch (Exception e) {
            processContext.setExceptMsg(e.getMessage());
        }
        return processContext;
    }

    protected abstract void paramCheck(ProcessContext<T> processContext) throws Exception;

    protected abstract void contextTypeCheck(ProcessContext<T> processContext) throws Exception;

    protected abstract void apiPathCheck(ProcessContext<T> processContext) throws Exception;

    protected abstract void headersCheck(ProcessContext<T> processContext) throws Exception;



}
