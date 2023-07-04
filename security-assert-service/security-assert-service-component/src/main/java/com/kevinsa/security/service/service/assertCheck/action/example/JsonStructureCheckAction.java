package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.service.common.impl.HashCommonServiceImpl;
import com.kevinsa.security.service.utils.JsonHierarchyParseUtils;
import com.kevinsa.security.service.utils.comparator.StringLengthAndCharAt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class JsonStructureCheckAction implements AssertStepAction<DefaultProcessContext> {

    @Autowired
    private JsonHierarchyParseUtils jsonHierarchyParseUtils;

    @Autowired
    private HashCommonServiceImpl hashCommonService;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;


    @Override
    public void process(DefaultProcessContext context) {
        try {
            FlowOriginDTO replayFlowDTO = context.getReplayFlowDTO();
            FlowOriginDTO flowOriginDTO = context.getFlowOriginDTO();
            List<String> respJsonTree = jsonHierarchyParseUtils.getJsonKey(replayFlowDTO.getResponseBody(), "", 1);
            respJsonTree.sort(new StringLengthAndCharAt());
            String replayRespJsonTreeHash = hashCommonService.getJsonTreeHash(flowOriginDTO.getBusiness(), flowOriginDTO.getApiHost(),
                    flowOriginDTO.getApiPath(), flowOriginDTO.getDataSource(), respJsonTree);
            if (!flowOriginDTO.getResponseJsonTreeHash().equals(replayRespJsonTreeHash)) {
                replayFlowDTO.setResponseJsonTreeHash(replayRespJsonTreeHash);
                replayFlowDTO.setVersion(flowOriginDTO.getVersion() + 1);
                replayFlowDTO.setCreateTime(System.currentTimeMillis() / 1000);
                replayFlowDTO.setApiHash(hashCommonService.getApiHash(flowOriginDTO.getBusiness(), flowOriginDTO.getApiHost(),
                        flowOriginDTO.getApiPath(), flowOriginDTO.getDataSource(), replayFlowDTO.getVersion()));
                flowCollectionMapper.insertData(replayFlowDTO);
            }
        } catch (Exception e) {
            context.setIsBreak(true);
            context.setExceptMsg("JsonStructureCheckAction error:" + e.getMessage());
        }
    }
}
