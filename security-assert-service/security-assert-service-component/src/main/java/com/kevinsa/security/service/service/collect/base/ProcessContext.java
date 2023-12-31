package com.kevinsa.security.service.service.collect.base;

public class ProcessContext<T> {

    private boolean filterResult;

    private String filterMsg;

    private T data;

    private String exceptMsg;

    private String bizMsg;

    public void setFilterResult(boolean result) {
        this.filterResult = result;
    }

    public boolean isFilterResult() {
        return filterResult;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setExceptMsg(String exceptMsg) {
        this.exceptMsg = exceptMsg;
    }

    public String getExceptMsg() {
        return exceptMsg;
    }

    public void setFilterMsg(String filterMsg) {
        this.filterMsg = filterMsg;
    }

    public String getFilterMsg() {
        return filterMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg;
    }

    public String getBizMsg() {
        return bizMsg;
    }
}
