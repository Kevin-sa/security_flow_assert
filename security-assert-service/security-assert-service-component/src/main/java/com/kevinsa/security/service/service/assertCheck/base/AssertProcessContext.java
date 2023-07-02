package com.kevinsa.security.service.service.assertCheck.base;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;

public class AssertProcessContext {

    public String bizId;

    private Boolean isBreak = false;

    public FlowOriginDTO flowData;

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

    public void setFlowData(FlowOriginDTO flowData) {
        this.flowData = flowData;
    }

    public FlowOriginDTO getFlowData() {
        return flowData;
    }

    public void contextClean() {
        this.bizId = "";
        this.isBreak = false;
        this.flowData = null;
    }
}
