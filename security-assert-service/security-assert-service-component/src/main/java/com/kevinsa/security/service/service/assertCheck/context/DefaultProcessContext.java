package com.kevinsa.security.service.service.assertCheck.context;

import com.kevinsa.security.service.dao.po.FlowOriginPO;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;

public class DefaultProcessContext extends AssertProcessContext {

    public FlowOriginPO flowOriginPO;

    public FlowOriginPO replayFlowDTO;

    public String resultMsg;

    public void setFlowOriginPO(FlowOriginPO flowOriginPO) {
        this.flowOriginPO = flowOriginPO;
    }

    public FlowOriginPO getFlowOriginPO() {
        return flowOriginPO;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setReplayFlowDTO(FlowOriginPO replayFlowDTO) {
        this.replayFlowDTO = replayFlowDTO;
    }

    public FlowOriginPO getReplayFlowDTO() {
        return replayFlowDTO;
    }

    public void remove() {
        this.setFlowOriginPO(null);
        this.setIsBreak(false);
        this.setExceptMsg(null);
    }
}
