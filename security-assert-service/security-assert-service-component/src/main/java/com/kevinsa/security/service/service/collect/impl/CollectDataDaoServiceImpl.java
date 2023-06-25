package com.kevinsa.security.service.service.collect.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.service.collect.CollectDataDaoService;
import com.kevinsa.security.service.utils.JsonHierarchyParseExtractor;
import com.kevinsa.security.service.utils.ObjectMapperUtils;

@Lazy
@Service
public class CollectDataDaoServiceImpl implements CollectDataDaoService {

    @Autowired
    private JsonHierarchyParseExtractor jsonHierarchyParseExtractor;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

    @Override
    public void flowDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, String business) {
        List<String> respJsonTree = jsonHierarchyParseExtractor.getJsonKey(responseInfoDTO.getBody(), "", 1);
        List<String> reqJsonTree = jsonHierarchyParseExtractor.getJsonKey(requestInfoDTO.getBody(), "", 1);
        FlowOriginDTO flowOriginDTO = FlowOriginDTO.builder()
                .business(business)
                .apiHost(requestInfoDTO.getHost())
                .apiPath(requestInfoDTO.getPath())
                .headersInfo(ObjectMapperUtils.toJSON(requestInfoDTO.getHeaders()))
                .requestPayload(requestInfoDTO.getBody())
                .requestJsonTree(ObjectMapperUtils.toJSON(reqJsonTree))
                .responseBody(responseInfoDTO.getBody())
                .responseJsonTree(ObjectMapperUtils.toJSON(respJsonTree))
                .createTime(System.currentTimeMillis() / 1000)
                .build();
        flowCollectionMapper.insertData(flowOriginDTO);
    }
}
