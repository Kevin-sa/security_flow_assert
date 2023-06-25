package com.kevinsa.security.service.service.collect.action.request;

import com.kevinsa.security.service.service.collect.base.FilterActionUnitTemplate;
import com.kevinsa.security.service.service.collect.base.ProcessContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class ReqDefaultFilterAction<RequestInfoDTO> extends FilterActionUnitTemplate<RequestInfoDTO> {
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
    protected void paramCheck(ProcessContext<RequestInfoDTO> processContext) throws Exception {
        if (processContext == null) {
            throw new Exception("processContext is null");
        }
    }

    @Override
    protected void contextTypeCheck(ProcessContext<RequestInfoDTO> processContext) {

    }

    @Override
    protected void apiPathCheck(ProcessContext<RequestInfoDTO> processContext) {

    }

    @Override
    protected void headersCheck(ProcessContext<RequestInfoDTO> processContext) {

    }

    @Override
    protected void statusCode(ProcessContext<RequestInfoDTO> processContext) {

    }
}
