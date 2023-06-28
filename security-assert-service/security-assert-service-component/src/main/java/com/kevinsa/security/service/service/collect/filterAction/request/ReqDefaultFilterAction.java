package com.kevinsa.security.service.service.collect.filterAction.request;

import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.service.collect.base.FilterActionUnitTemplate;
import com.kevinsa.security.service.service.collect.base.ProcessContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ReqDefaultFilterAction<T> extends FilterActionUnitTemplate<T> {
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

    private final List<String> apiHostBlackList = new ArrayList<>(Collections.emptyList());
    private final List<String> apiPathBlackList = new ArrayList<>(Collections.emptyList());


    @Override
    protected void paramCheck(ProcessContext<T> processContext) throws Exception {
        if (processContext == null || processContext.getData() == null) {
            throw new Exception("processContext is null");
        }
    }

    @Override
    protected void blackListCheck(ProcessContext<T> processContext) throws Exception {
        RequestInfoDTO requestInfoDTO = (RequestInfoDTO) processContext.getData();
        if (apiHostBlackList.contains(requestInfoDTO.getHost()) ||
                apiPathBlackList.contains(requestInfoDTO.getPath())) {
            throw new Exception(this.getClass().getName() + "blackListCheck check error");
        }
    }

    @Override
    protected void contextTypeCheck(ProcessContext<T> processContext) {
        RequestInfoDTO requestInfoDTO = (RequestInfoDTO) processContext.getData();
        if (!requestInfoDTO.getHeaders().get("Content-Type").startsWith("application/json")) {
            processContext.setFilterResult(false);
            processContext.setFilterMsg(this.getClass().getName() + " contextTypeCheck check error");
        }
    }

    @Override
    protected void headersCheck(ProcessContext<T> processContext) {

    }

    @Override
    protected void statusCode(ProcessContext<T> processContext) {

    }
}
