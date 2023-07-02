package com.kevinsa.security.service.service.assertCheck.base;


public class AssertProcessContext {

    public String bizId;

    public String bizMsg;

    private Boolean isBreak = false;

    private String exceptMsg = "";

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public void setIsBreak(boolean isBreak) {
        this.isBreak = isBreak;
    }

    public Boolean getIsBreak() {
        return isBreak;
    }

    public void setExceptMsg(String exceptMsg) {
        this.exceptMsg = exceptMsg;
    }

    public String getExceptMsg() {
        return exceptMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg;
    }

    public String getBizMsg() {
        return bizMsg;
    }
}
