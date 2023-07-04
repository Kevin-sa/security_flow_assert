package com.kevinsa.security.service.service.assertCheck.action.example;

import com.jayway.jsonpath.JsonPath;
import com.kevinsa.security.service.dao.dto.AssetJsonPathRuleDTO;
import com.kevinsa.security.service.dao.dto.AssetJsonPathRuleDataDTO;
import com.kevinsa.security.service.dao.dto.SecurityAssetResultDTO;
import com.kevinsa.security.service.dao.mapper.AssetJsonPathRuleMapper;
import com.kevinsa.security.service.dao.mapper.SecurityAssetResultMapper;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.utils.ObjectMapperUtils;

import javax.annotation.Resource;

import static com.kevinsa.security.service.enums.JsonPathTypeEnums.REQUEST_SUCCESS;
import static com.kevinsa.security.service.enums.JsonPathTypeEnums.SECURITY_ASSET;
import static com.kevinsa.security.service.enums.OriginFlowDataStatusEnums.ENABLE;

public class JsonPathCheckAction implements AssertStepAction<DefaultProcessContext> {

    @Resource
    private AssetJsonPathRuleMapper assetJsonPathRuleMapper;

    @Resource
    private SecurityAssetResultMapper securityAssetResultMapper;

    /**
     * 两部分逻辑
     * 1、判断是否符合检测条件
     * 2、是否有asset断言条件
     */
    @Override
    public void process(DefaultProcessContext context) {
        String responseBody = context.getReplayFlowDTO().getResponseBody();
        reqSuccessCheck(responseBody, context);

        if (context.getIsBreak()) {
            return;
        }
        securityAssetCheck(responseBody, context);
    }

    private void reqSuccessCheck(String responseBody, DefaultProcessContext context) {
        AssetJsonPathRuleDTO reqSuccessRuleDTO = assetJsonPathRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), REQUEST_SUCCESS.getTypeId(), context.getReplayFlowDTO().getBusiness(),
                context.getReplayFlowDTO().getApiHost(), context.getReplayFlowDTO().getApiPath()
        );
        if (reqSuccessRuleDTO == null) {
            return;
        }
        AssetJsonPathRuleDataDTO reqSuccessRule = ObjectMapperUtils.fromJSON(reqSuccessRuleDTO.getData(),
                AssetJsonPathRuleDataDTO.class);
        Object respValue = JsonPath.read(responseBody, reqSuccessRule.getJsonPath());
        if (respValue != reqSuccessRule.getValue()) {
            context.setIsBreak(true);
        }
    }

    private void securityAssetCheck(String responseBody, DefaultProcessContext context) {
        AssetJsonPathRuleDTO assetRuleDTO = assetJsonPathRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), SECURITY_ASSET.getTypeId(), context.getReplayFlowDTO().getBusiness(),
                context.getReplayFlowDTO().getApiHost(), context.getReplayFlowDTO().getApiPath()
        );
        if (assetRuleDTO == null) {
            return;
        }
        AssetJsonPathRuleDataDTO assetRule = ObjectMapperUtils.fromJSON(assetRuleDTO.getData(),
                AssetJsonPathRuleDataDTO.class);
        Object respValue = JsonPath.read(responseBody, assetRule.getJsonPath());

        if (respValue != assetRule.getValue()) {
            SecurityAssetResultDTO securityAssetResultDTO = SecurityAssetResultDTO.builder()
                    .ruleId(assetRuleDTO.getId())
                    .flowId(context.getFlowOriginDTO().getId())
                    .responseBody(responseBody)
                    .diffValue((String) respValue)
                    .createTime(System.currentTimeMillis() / 1000)
                    .build();
            securityAssetResultMapper.insert(securityAssetResultDTO);
        }

    }
}
