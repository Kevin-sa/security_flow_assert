package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.dao.po.FlowOriginPO;
import com.kevinsa.security.service.enums.StateMachineStatusEnums;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.service.dao.ClickHouseFactoryService;
import com.kevinsa.security.service.utils.HttpClientUtils;
import com.kevinsa.security.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HttpReplayCheckAction implements AssertStepAction<DefaultProcessContext> {

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private ClickHouseFactoryService clickHouseFactoryService;

    @Override
    public void process(DefaultProcessContext context) {
        try {
            FlowOriginPO flowOriginPO = context.getFlowOriginPO();
            String requestUrl = "https://" + flowOriginPO.getApiHost() + flowOriginPO.getApiPath();
            Map<String, String> headerInfos = ObjectMapperUtils.fromJSON(flowOriginPO.getHeadersInfo(), Map.class);
            String replayBody = "";
            if (!flowOriginPO.getMethod().equals("POST") && flowOriginPO.getMethod().equals("GET")) {
                context.setIsBreak(true);
                context.setResultMsg("HttpReplayCheckAction method:" + flowOriginPO.getMethod());
            }
            if (flowOriginPO.getMethod().equals("POST")) {
                replayBody = httpClientUtils.doPost(requestUrl, headerInfos, flowOriginPO.getRequestPayload());
            }
            if (flowOriginPO.getMethod().equals("GET")) {
                replayBody = httpClientUtils.doGet(requestUrl, headerInfos);
            }
            FlowOriginPO replayFlowDTO = flowOriginPO;
            replayFlowDTO.setResponseBody(replayBody);
            context.setReplayFlowDTO(replayFlowDTO);

            loadOtherFactory(flowOriginPO, replayBody);
        } catch (Exception e) {
            context.setIsBreak(true);
            context.setExceptMsg("HttpReplayCheckAction error:" + e.getMessage());
        }
    }

    private void loadOtherFactory(FlowOriginPO flowOriginPO, String body) {
        clickHouseFactoryService.runnerAssertTaskEntrance(flowOriginPO, body
                , StateMachineStatusEnums.RUNNER_ASSERT_TASK.getStatusId());
    }
}
