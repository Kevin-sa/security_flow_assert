package com.kevinsa.security.service.service.collect.action;

import com.kevinsa.security.service.service.collect.base.FilterActionUnitTemplate;
import com.kevinsa.security.service.service.collect.base.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class DefaultFilterAction<RequestInfoDTO> extends FilterActionUnitTemplate<RequestInfoDTO> {
    @Override
    public Pattern getPattern() {
        return Pattern.compile("kwaixiaodian\\.com");
    }

    @Override
    public String getBizMsg() {
        return "kwai-shop";
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
}
