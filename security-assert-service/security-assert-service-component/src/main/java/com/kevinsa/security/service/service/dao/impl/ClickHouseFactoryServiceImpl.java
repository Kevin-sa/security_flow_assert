package com.kevinsa.security.service.service.dao.impl;

import com.kevinsa.security.service.dao.clickhouse.ClickHouseFactory;
import com.kevinsa.security.service.dao.po.ClickHouseBaseInfoPO;
import com.kevinsa.security.service.dao.po.ClickHouseExtendsInfoPO;
import com.kevinsa.security.service.dao.po.FlowOriginPO;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.service.dao.ClickHouseFactoryService;
import com.kevinsa.security.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClickHouseFactoryServiceImpl implements ClickHouseFactoryService {

    @Autowired
    private ClickHouseFactory clickHouseFactory;

    @Override
    public void pluginCollectEntrance(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, int statusId) {
        String uuid = requestInfoDTO == null ? responseInfoDTO.getUuid() : requestInfoDTO.getUuid();
        ClickHouseBaseInfoPO clickHouseBaseInfoPO = ClickHouseBaseInfoPO.builder()
                .statusId(statusId)
                .host(requestInfoDTO == null ? "" :requestInfoDTO.getHost())
                .path(requestInfoDTO == null ? "" :requestInfoDTO.getPath())
                .uuid(uuid)
                .build();
        ClickHouseExtendsInfoPO clickHouseExtendsInfoPO = ClickHouseExtendsInfoPO.builder()
                .request(requestInfoDTO)
                .response(responseInfoDTO)
                .build();
        clickHouseFactory.insertFlowRecord(clickHouseBaseInfoPO, clickHouseExtendsInfoPO);
    }

    @Override
    public void runnerAssertTaskEntrance(FlowOriginPO flowOriginPO, String body, int statusId) {
        ClickHouseBaseInfoPO clickHouseBaseInfoPO = ClickHouseBaseInfoPO.builder()
                .statusId(statusId)
                .host(flowOriginPO.getApiHost())
                .path(flowOriginPO.getApiPath())
                .build();

        ResponseInfoDTO responseInfoDTO = ResponseInfoDTO.builder()
                .body(body)
                .build();

        RequestInfoDTO requestInfoDTO = RequestInfoDTO.builder()
                .method(flowOriginPO.getMethod())
                .headers(ObjectMapperUtils.fromJSON(flowOriginPO.getHeadersInfo(), Map.class))
                .body(flowOriginPO.getRequestPayload())
                .build();

        ClickHouseExtendsInfoPO clickHouseExtendsInfoPO = ClickHouseExtendsInfoPO.builder()
                .request(requestInfoDTO)
                .response(responseInfoDTO)
                .build();
        clickHouseFactory.insertFlowRecord(clickHouseBaseInfoPO, clickHouseExtendsInfoPO);
    }


}
