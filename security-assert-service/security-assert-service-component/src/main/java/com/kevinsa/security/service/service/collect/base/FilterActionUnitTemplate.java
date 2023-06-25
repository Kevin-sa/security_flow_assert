package com.kevinsa.security.service.service.collect.base;

public abstract class FilterActionUnitTemplate<T> implements FilterActionUnit<T> {

    @Override
    public ProcessContext<T> execute(ProcessContext<T> processContext) {
        try {
            paramCheck(processContext);
            blackListCheck(processContext);

            contextTypeCheck(processContext);
            if (!processContext.isFilterResult()) return processContext;
            statusCode(processContext);
            if (!processContext.isFilterResult()) return processContext;
            headersCheck(processContext);
            if (!processContext.isFilterResult()) return processContext;
            return processContext;
        } catch (Exception e) {
            processContext.setExceptMsg(e.getMessage());
        }
        return processContext;
    }

    protected abstract void paramCheck(ProcessContext<T> processContext) throws Exception;

    protected abstract void blackListCheck(ProcessContext<T> processContext) throws Exception;

    protected abstract void contextTypeCheck(ProcessContext<T> processContext);

    protected abstract void headersCheck(ProcessContext<T> processContext);

    protected abstract void statusCode(ProcessContext<T> processContext);


}
