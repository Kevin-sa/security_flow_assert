package com.kevinsa.security.service.service.collect.filterAction.response;


import com.kevinsa.security.service.dto.ResponseInfoDTO;
import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.collect.base.FilterActionUnitTemplate;
import com.kevinsa.security.service.service.collect.base.ProcessContext;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class RespDefaultFilterAction<T> extends FilterActionUnitTemplate<T> {

    private final List<Short> statusCodeWhiteList = Arrays.asList((short)200, (short)302);

    @Override
    public String getPattern() {
        return "(.)*\\.kwaixiaodian\\.com";
    }

    @Override
    public String getBizMsg() {
        return "kwai-shop";
    }

    @Override
    public boolean isEnable() {
        return true;
    }

    @Override
    protected void paramCheck(ProcessContext<T> processContext) throws Exception {
        if (processContext == null || processContext.getData() == null) {
            throw new Exception("processContext is null");
        }
    }

    @Override
    protected void blackListCheck(ProcessContext<T> processContext) throws Exception {

    }

    @Override
    protected void contextTypeCheck(ProcessContext<T> processContext) {

    }


    @Override
    protected void headersCheck(ProcessContext<T> processContext) {

    }

    @Override
    protected void statusCodeCheck(ProcessContext<T> processContext) {
        ResponseInfoDTO responseInfoDTO = ((ResponseInfoDTO) processContext.getData());
        if (!statusCodeWhiteList.contains(responseInfoDTO.getStatusCode())) {
            processContext.setFilterResult(false);
            processContext.setFilterMsg(this.getClass().getName() + " statusCode check error");
        }
    }

    @Override
    protected void loadOtherChecks(ProcessContext<T> processContext) {

    }

}
