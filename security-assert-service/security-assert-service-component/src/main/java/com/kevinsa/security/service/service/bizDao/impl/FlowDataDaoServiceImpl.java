package com.kevinsa.security.service.service.bizDao.impl;

import static com.kevinsa.security.service.enums.PluginDataSourceEnums.BURPSUITE;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.service.bizDao.FlowDataDaoService;
import com.kevinsa.security.service.utils.HashUtils;
import com.kevinsa.security.service.utils.JsonHierarchyParseUtils;
import com.kevinsa.security.service.utils.ObjectMapperUtils;

@Lazy
@Service
public class FlowDataDaoServiceImpl implements FlowDataDaoService {

    @Autowired
    private JsonHierarchyParseUtils jsonHierarchyParseUtils;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

    @Autowired
    private HashUtils hashUtils;

    /**
     * 如果不存在历史数据则直接insert；
     * 如果存在历史数据且request or response json tree数据不一致，则version + 1
     */
    @Override
    public void flowDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, String business) {
        List<String> respJsonTree = jsonHierarchyParseUtils.getJsonKey(responseInfoDTO.getBody(), "", 1);
        List<String> reqJsonTree = jsonHierarchyParseUtils.getJsonKey(requestInfoDTO.getBody(), "", 1);
        int source = BURPSUITE.getSource();
        Integer version;
        version = flowCollectionMapper.selectMaxVersionByApiInfo(business, requestInfoDTO.getHost(), requestInfoDTO.getPath(), source);

        FlowOriginDTO flowOriginDTO = FlowOriginDTO.builder()
                .business(business)
                .apiHost(requestInfoDTO.getHost())
                .apiPath(requestInfoDTO.getPath())
                .headersInfo(ObjectMapperUtils.toJSON(requestInfoDTO.getHeaders()))
                .requestPayload(requestInfoDTO.getBody())
                .requestJsonTree(ObjectMapperUtils.toJSON(reqJsonTree))
                .requestJsonTreeHash(hashUtils.md5(ObjectMapperUtils.toJSON(reqJsonTree)))
                .responseBody(responseInfoDTO.getBody())
                .responseJsonTree(ObjectMapperUtils.toJSON(respJsonTree))
                .responseJsonTreeHash(hashUtils.md5(ObjectMapperUtils.toJSON(respJsonTree)))
                .dataSource(source)
                .version(1)
                .apiHash(getApiHash(business, requestInfoDTO.getHost(), requestInfoDTO.getPath(), source))
                .createTime(System.currentTimeMillis() / 1000)
                .build();
        if (version != null) {
            flowOriginDTO.setVersion(version + 1);
        }
        flowCollectionMapper.insertData(flowOriginDTO);
    }

    private String getApiHash(String business, String apiHost, String apiPath, int dataSource) {
        String message = business + "|" + apiHost + "|" + apiPath + "|" + dataSource;
        return hashUtils.md5(message);
    }

}
