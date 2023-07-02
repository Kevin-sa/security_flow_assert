package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.utils.HttpClientUtils;
import com.kevinsa.security.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HttpReplayCheckAction implements AssertStepAction<DefaultProcessContext> {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Override
    public void process(DefaultProcessContext context) {
        try {
            FlowOriginDTO flowOriginDTO = context.getFlowOriginDTO();
            String requestUrl = "https://" + flowOriginDTO.getApiHost() + flowOriginDTO.getApiPath();
            Map<String, String> headerInfos = ObjectMapperUtils.fromJSON(flowOriginDTO.getHeadersInfo(), Map.class);
            String replayBody = "";
            if (!flowOriginDTO.getMethod().equals("POST") && flowOriginDTO.getMethod().equals("GET")) {
                context.setIsBreak(true);
                context.setResultMsg("HttpReplayCheckAction method:" + flowOriginDTO.getMethod());
            }
            if (flowOriginDTO.getMethod().equals("POST")) {
                replayBody = httpClientUtils.doPost(requestUrl, headerInfos, flowOriginDTO.getRequestPayload());
            }
            if (flowOriginDTO.getMethod().equals("GET")) {
                replayBody = httpClientUtils.doGet(requestUrl, headerInfos);
            }
            FlowOriginDTO replayFlowDTO = flowOriginDTO;
            replayFlowDTO.setResponseBody(replayBody);
        } catch (Exception e) {
            context.setIsBreak(true);
            context.setExceptMsg("HttpReplayCheckAction error:" + e.getMessage());
        }
    }
}
