package com.kevinsa.security.service.service.assertCheck.action.example;

import com.jayway.jsonpath.JsonPath;
import com.kevinsa.security.service.dao.dto.AssetJsonPathRuleDTO;
import com.kevinsa.security.service.dao.dto.AssetJsonPathRuleDataDTO;
import com.kevinsa.security.service.dao.dto.AssetResultDiffValueDTO;
import com.kevinsa.security.service.dao.dto.SecurityAssetResultDTO;
import com.kevinsa.security.service.dao.mapper.AssetActionRuleMapper;
import com.kevinsa.security.service.dao.mapper.SecurityAssetResultMapper;
import com.kevinsa.security.service.enums.AssertRuleTypeEnums;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import com.kevinsa.security.service.service.operator.base.OperatorUnitServiceHelper;
import com.kevinsa.security.service.utils.ObjectMapperUtils;

import javax.annotation.Resource;

import java.util.Collections;

import static com.kevinsa.security.service.enums.OriginFlowDataStatusEnums.ENABLE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonPathCheckAction implements AssertStepAction<DefaultProcessContext> {

    private static final Logger logger = LoggerFactory.getLogger(JsonPathCheckAction.class);

    @Resource
    private AssetActionRuleMapper assetActionRuleMapper;

    @Resource
    private SecurityAssetResultMapper securityAssetResultMapper;

    @Autowired
    private OperatorUnitServiceHelper operatorUnitServiceHelper;

    /**
     * 两部分逻辑
     * 1、判断是否符合检测条件
     * 2、是否有asset断言条件
     */
    @Override
    public void process(DefaultProcessContext context) {
        try {
            String responseBody = context.getReplayFlowDTO().getResponseBody();
            reqSuccessCheck(responseBody, context);

            if (context.getIsBreak()) {
                return;
            }
            securityAssetCheck(responseBody, context);
        } catch (Exception e) {
            logger.error("JsonPathCheckAction process error:", e);
        }
    }

    private <T extends Comparable<T>> void reqSuccessCheck(String responseBody, DefaultProcessContext context) throws Exception {
        AssetJsonPathRuleDTO reqSuccessRuleDTO = assetActionRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), AssertRuleTypeEnums.REQUEST_SUCCESS.getTypeId(), context.getReplayFlowDTO().getBusiness(),
                context.getReplayFlowDTO().getApiHost(), context.getReplayFlowDTO().getApiPath()
        );
        if (reqSuccessRuleDTO == null) {
            return;
        }
        AssetJsonPathRuleDataDTO reqSuccessRule = ObjectMapperUtils.fromJSON(reqSuccessRuleDTO.getData(),
                AssetJsonPathRuleDataDTO.class);
        Object respValue = JsonPath.read(responseBody, reqSuccessRule.getJsonPath());
        boolean result = operatorUnitServiceHelper.execute(reqSuccessRule.getOperatorType(), (T) respValue, (T) reqSuccessRule.getRightValue());
        if (!result) {
            context.setIsBreak(true);
        }
    }

    private <T extends Comparable<T>> void securityAssetCheck(String responseBody, DefaultProcessContext context) throws Exception {
        AssetJsonPathRuleDTO assetRuleDTO = assetActionRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), AssertRuleTypeEnums.SECURITY_ASSET.getTypeId(), context.getReplayFlowDTO().getBusiness(),
                context.getReplayFlowDTO().getApiHost(), context.getReplayFlowDTO().getApiPath()
        );
        if (assetRuleDTO == null) {
            return;
        }
        AssetJsonPathRuleDataDTO assetRule = ObjectMapperUtils.fromJSON(assetRuleDTO.getData(),
                AssetJsonPathRuleDataDTO.class);
        Object respValue = JsonPath.read(responseBody, assetRule.getJsonPath());

        AssetResultDiffValueDTO assetResultDiffValueDTO = AssetResultDiffValueDTO.builder()
                .value(Collections.singletonList(respValue))
                .build();

        boolean result = operatorUnitServiceHelper.execute(assetRule.getOperatorType(), (T) respValue, (T) assetRule.getRightValue());

        if (!result) {
            SecurityAssetResultDTO securityAssetResultDTO = SecurityAssetResultDTO.builder()
                    .ruleId(assetRuleDTO.getId())
                    .flowId(context.getFlowOriginDTO().getId())
                    .replayFlowId(0L)
                    .responseBody(responseBody)
                    .diffValue(ObjectMapperUtils.toJSON(assetResultDiffValueDTO))
                    .createTime(System.currentTimeMillis() / 1000)
                    .build();
            securityAssetResultMapper.insert(securityAssetResultDTO);
        }

    }
}
