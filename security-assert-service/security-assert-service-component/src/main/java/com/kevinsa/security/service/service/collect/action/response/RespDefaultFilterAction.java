package com.kevinsa.security.service.service.collect.action.response;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.collect.base.FilterActionUnitTemplate;
import com.kevinsa.security.service.service.collect.base.ProcessContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RespDefaultFilterAction<ResponseInfoDTO> extends FilterActionUnitTemplate<ResponseInfoDTO> {
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
    protected void paramCheck(ProcessContext<ResponseInfoDTO> processContext) throws Exception {

    }

    @Override
    protected void contextTypeCheck(ProcessContext<ResponseInfoDTO> processContext) {

    }

    @Override
    protected void apiPathCheck(ProcessContext<ResponseInfoDTO> processContext) {

    }

    @Override
    protected void headersCheck(ProcessContext<ResponseInfoDTO> processContext) {

    }

    @Override
    protected void statusCode(ProcessContext<ResponseInfoDTO> processContext) {

    }
}
