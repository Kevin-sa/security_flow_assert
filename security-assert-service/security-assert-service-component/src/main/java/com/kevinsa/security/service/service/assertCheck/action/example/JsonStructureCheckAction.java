package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.utils.HashUtils;
import com.kevinsa.security.service.utils.JsonHierarchyParseUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class JsonStructureCheckAction implements AssertStepAction<DefaultProcessContext> {

    @Autowired
    private JsonHierarchyParseUtils jsonHierarchyParseUtils;

    @Autowired
    private HashUtils hashUtils;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;


    @Override
    public void process(DefaultProcessContext context) {
        try {
            FlowOriginDTO replayFlowDTO = context.getReplayFlowDTO();
            FlowOriginDTO flowOriginDTO = context.getFlowOriginDTO();
            List<String> respJsonTree = jsonHierarchyParseUtils.getJsonKey(replayFlowDTO.getResponseBody(), "", 1);
            String replayRespJsonTreeHash = getJsonTreeHash(flowOriginDTO.getBusiness(), flowOriginDTO.getApiHost(),
                    flowOriginDTO.getApiPath(), flowOriginDTO.getDataSource(), respJsonTree);
            if (!flowOriginDTO.getRequestJsonTreeHash().equals(replayRespJsonTreeHash)) {
                replayFlowDTO.setRequestJsonTreeHash(replayRespJsonTreeHash);
                replayFlowDTO.setVersion(flowOriginDTO.getVersion() + 1);
                replayFlowDTO.setCreateTime(System.currentTimeMillis() / 1000);
                replayFlowDTO.setApiHash(getApiHash(flowOriginDTO.getBusiness(), flowOriginDTO.getApiHost(),
                        flowOriginDTO.getApiPath(), flowOriginDTO.getDataSource(), replayFlowDTO.getVersion()));
                flowCollectionMapper.insertData(replayFlowDTO);
            }
        } catch (Exception e) {
            context.setIsBreak(true);
            context.setExceptMsg("JsonStructureCheckAction error:" + e.getMessage());
        }
    }

    private String getJsonTreeHash(String business, String apiHost, String apiPath, int dataSource,
                                   List<String> jsonTreeList) {
        String message = business + "|" + apiHost + "|" + apiPath + "|" + dataSource + "|"
                + String.join(",", jsonTreeList);
        return hashUtils.md5(message);
    }

    private String getApiHash(String business, String apiHost, String apiPath, int dataSource, int version) {
        String message = business + "|" + apiHost + "|" + apiPath + "|" + dataSource + "|" + version;
        return hashUtils.md5(message);
    }
}
