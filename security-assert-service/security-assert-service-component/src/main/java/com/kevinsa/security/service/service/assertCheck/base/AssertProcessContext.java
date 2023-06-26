package com.kevinsa.security.service.service.assertCheck.base;

public class AssertProcessContext {

    public String bizId;

    private Boolean isBreak = false;

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
}
