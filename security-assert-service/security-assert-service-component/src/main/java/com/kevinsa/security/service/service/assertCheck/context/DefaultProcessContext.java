package com.kevinsa.security.service.service.assertCheck.context;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;

public class DefaultProcessContext extends AssertProcessContext {

    public FlowOriginDTO flowOriginDTO;

    public FlowOriginDTO replayFlowDTO;

    public String resultMsg;

    public void setFlowOriginDTO(FlowOriginDTO flowOriginDTO) {
        this.flowOriginDTO = flowOriginDTO;
    }

    public FlowOriginDTO getFlowOriginDTO() {
        return flowOriginDTO;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setReplayFlowDTO(FlowOriginDTO replayFlowDTO) {
        this.replayFlowDTO = replayFlowDTO;
    }

    public FlowOriginDTO getReplayFlowDTO() {
        return replayFlowDTO;
    }

    public void remove() {
        this.setFlowOriginDTO(null);
        this.setIsBreak(false);
        this.setExceptMsg(null);
    }
}
