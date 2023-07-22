package com.kevinsa.security.service.service.assertCheck.action.example;

import com.kevinsa.security.service.dao.po.AssetResultDiffValuePO;
import com.kevinsa.security.service.dao.po.FlowOriginPO;
import com.kevinsa.security.service.dao.po.SecurityAssetResultPO;
import com.kevinsa.security.service.dao.mapper.AssetActionRuleMapper;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.dao.mapper.SecurityAssetResultMapper;
import com.kevinsa.security.service.enums.AssertRuleTypeEnums;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.service.common.impl.HashCommonServiceImpl;
import com.kevinsa.security.service.utils.JsonHierarchyParseUtils;
import com.kevinsa.security.service.utils.ObjectMapperUtils;
import com.kevinsa.security.service.utils.comparator.StringLengthAndCharAt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonStructureCheckAction implements AssertStepAction<DefaultProcessContext> {

    @Autowired
    private JsonHierarchyParseUtils jsonHierarchyParseUtils;

    @Autowired
    private HashCommonServiceImpl hashCommonService;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

    @Resource
    private SecurityAssetResultMapper securityAssetResultMapper;

    @Resource
    private AssetActionRuleMapper assetActionRuleMapper;

    @Override
    public void process(DefaultProcessContext context) {
        try {
            FlowOriginPO replayFlowDTO = context.getReplayFlowDTO();
            FlowOriginPO flowOriginPO = context.getFlowOriginPO();
            List<String> respJsonTree = jsonHierarchyParseUtils.getJsonKey(replayFlowDTO.getResponseBody(), "", 1);
            respJsonTree.sort(new StringLengthAndCharAt());
            String replayRespJsonTreeHash = hashCommonService.getJsonTreeHash(flowOriginPO.getBusiness(), flowOriginPO.getApiHost(),
                    flowOriginPO.getApiPath(), flowOriginPO.getDataSource(), respJsonTree);
            if (!flowOriginPO.getResponseJsonTreeHash().equals(replayRespJsonTreeHash)) {
                replayFlowDTO.setResponseJsonTree(ObjectMapperUtils.toJSON(respJsonTree));
                replayFlowDTO.setResponseJsonTreeHash(replayRespJsonTreeHash);
                replayFlowDTO.setVersion(flowOriginPO.getVersion() + 1);
                replayFlowDTO.setCreateTime(System.currentTimeMillis() / 1000);
                replayFlowDTO.setApiHash(hashCommonService.getApiHash(flowOriginPO.getBusiness(), flowOriginPO.getApiHost(),
                        flowOriginPO.getApiPath(), flowOriginPO.getDataSource(), replayFlowDTO.getVersion()));
                long replayId = flowCollectionMapper.insertData(replayFlowDTO);
                insertAssetResult(replayFlowDTO.getId(), replayId, replayFlowDTO.getResponseBody(),
                        flowOriginPO.getRequestJsonTree(), respJsonTree);
            }
        } catch (Exception e) {
            context.setIsBreak(true);
            context.setExceptMsg("JsonStructureCheckAction error:" + e.getMessage());
        }
    }

    private void insertAssetResult(Long id, long replayId, String respBody, String respJsonTreeStr, List<String> replayRespJsonTree) {
        List<String> respJsonTree = ObjectMapperUtils.fromJSON(respJsonTreeStr, List.class);
        List<String> jsonTreeAll = new ArrayList<>();
        jsonTreeAll.addAll(respJsonTree);
        jsonTreeAll.addAll(replayRespJsonTree);

        List<Object> differenceList = jsonTreeAll.stream()
                .filter(element -> !respJsonTree.contains(element))
                .collect(Collectors.toList());

        AssetResultDiffValuePO assetResultDiffValuePO = AssetResultDiffValuePO.builder()
                .value(differenceList)
                .build();

        Long defaultRuleId = 0L;
        defaultRuleId = assetActionRuleMapper.getIdByType(AssertRuleTypeEnums.DEFAULT.getTypeId());
        SecurityAssetResultPO securityAssetResultPO = SecurityAssetResultPO.builder()
                .ruleId(defaultRuleId)
                .flowId(id)
                .replayFlowId(replayId)
                .responseBody(respBody)
                .diffValue(ObjectMapperUtils.toJSON(assetResultDiffValuePO))
                .createTime(System.currentTimeMillis() / 1000)
                .build();
        securityAssetResultMapper.insert(securityAssetResultPO);
    }
}
