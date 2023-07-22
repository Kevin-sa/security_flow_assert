package com.kevinsa.security.service.service.assertCheck.action.example;

import com.jayway.jsonpath.JsonPath;
import com.kevinsa.security.service.dao.po.AssetJsonPathRulePO;
import com.kevinsa.security.service.dao.po.AssetJsonPathRuleDataPO;
import com.kevinsa.security.service.dao.po.AssetResultDiffValuePO;
import com.kevinsa.security.service.dao.po.SecurityAssetResultPO;
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
        AssetJsonPathRulePO reqSuccessRulePO = assetActionRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), AssertRuleTypeEnums.REQUEST_SUCCESS.getTypeId(), context.getReplayFlowDTO().getBusiness(),
                context.getReplayFlowDTO().getApiHost(), context.getReplayFlowDTO().getApiPath()
        );
        if (reqSuccessRulePO == null) {
            return;
        }
        AssetJsonPathRuleDataPO reqSuccessRule = ObjectMapperUtils.fromJSON(reqSuccessRulePO.getData(),
                AssetJsonPathRuleDataPO.class);
        Object respValue = JsonPath.read(responseBody, reqSuccessRule.getJsonPath());
        boolean result = operatorUnitServiceHelper.execute(reqSuccessRule.getOperatorType(), (T) respValue, (T) reqSuccessRule.getRightValue());
        if (!result) {
            context.setIsBreak(true);
        }
    }

    private <T extends Comparable<T>> void securityAssetCheck(String responseBody, DefaultProcessContext context) throws Exception {
        AssetJsonPathRulePO assetRulePO = assetActionRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), AssertRuleTypeEnums.SECURITY_ASSET.getTypeId(), context.getReplayFlowDTO().getBusiness(),
                context.getReplayFlowDTO().getApiHost(), context.getReplayFlowDTO().getApiPath()
        );
        if (assetRulePO == null) {
            return;
        }
        AssetJsonPathRuleDataPO assetRule = ObjectMapperUtils.fromJSON(assetRulePO.getData(),
                AssetJsonPathRuleDataPO.class);
        Object respValue = JsonPath.read(responseBody, assetRule.getJsonPath());

        AssetResultDiffValuePO assetResultDiffValuePO = AssetResultDiffValuePO.builder()
                .value(Collections.singletonList(respValue))
                .build();

        boolean result = operatorUnitServiceHelper.execute(assetRule.getOperatorType(), (T) respValue, (T) assetRule.getRightValue());

        if (!result) {
            SecurityAssetResultPO securityAssetResultPO = SecurityAssetResultPO.builder()
                    .ruleId(assetRulePO.getId())
                    .flowId(context.getFlowOriginPO().getId())
                    .replayFlowId(0L)
                    .responseBody(responseBody)
                    .diffValue(ObjectMapperUtils.toJSON(assetResultDiffValuePO))
                    .createTime(System.currentTimeMillis() / 1000)
                    .build();
            securityAssetResultMapper.insert(securityAssetResultPO);
        }

    }
}
