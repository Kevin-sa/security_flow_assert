package com.kevinsa.security.service.service.bizDao.impl;

import static com.kevinsa.security.service.enums.PluginDataSourceEnums.BURPSUITE;

import java.util.List;

import javax.annotation.Resource;

import com.kevinsa.security.service.dao.mapper.AssetActionRuleMapper;
import com.kevinsa.security.service.enums.OriginFlowDataStatusEnums;
import com.kevinsa.security.service.service.common.impl.HashCommonServiceImpl;
import com.kevinsa.security.service.utils.comparator.StringLengthAndCharAt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.service.bizDao.FlowDataDaoService;
import com.kevinsa.security.service.utils.JsonHierarchyParseUtils;
import com.kevinsa.security.service.utils.ObjectMapperUtils;

@Slf4j
@Lazy
@Service
public class FlowDataDaoServiceImpl implements FlowDataDaoService {

    private final String PREFIX = "FlowDataDaoServiceImpl ->";

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

    @Resource
    private AssetActionRuleMapper assetActionRuleMapper;

    @Autowired
    private JsonHierarchyParseUtils jsonHierarchyParseUtils;

    @Autowired
    private HashCommonServiceImpl hashCommonService;

    /**
     * 如果不存在历史数据则直接insert；
     * 如果存在历史数据且request or response json tree数据不一致，则version + 1
     */
    @Override
    public void flowDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, String business) {
        try {
            List<String> reqJsonTree = jsonHierarchyParseUtils.getJsonKey(requestInfoDTO.getBody(), "", 1);
            reqJsonTree.sort(new StringLengthAndCharAt());
            List<String> respJsonTree = jsonHierarchyParseUtils.getJsonKey(responseInfoDTO.getBody(), "", 1);
            respJsonTree.sort(new StringLengthAndCharAt());
            int source = BURPSUITE.getSource();
            Integer version;
            version = flowCollectionMapper.selectMaxVersionByApiInfo(business, requestInfoDTO.getHost(), requestInfoDTO.getPath(), source);

            FlowOriginDTO flowOriginDTO = FlowOriginDTO.builder()
                    .business(business)
                    .apiHost(requestInfoDTO.getHost())
                    .apiPath(requestInfoDTO.getPath())
                    .method(requestInfoDTO.getMethod())
                    .headersInfo(ObjectMapperUtils.toJSON(requestInfoDTO.getHeaders()))
                    .requestPayload(requestInfoDTO.getBody())
                    .requestJsonTree(ObjectMapperUtils.toJSON(reqJsonTree))
                    .requestJsonTreeHash(hashCommonService.getJsonTreeHash(business, requestInfoDTO.getHost(), requestInfoDTO.getPath(),
                            source, respJsonTree))
                    .responseBody(responseInfoDTO.getBody())
                    .responseJsonTree(ObjectMapperUtils.toJSON(respJsonTree))
                    .responseJsonTreeHash(hashCommonService.getJsonTreeHash(business, requestInfoDTO.getHost(), requestInfoDTO.getPath(),
                            source, respJsonTree))
                    .dataSource(source)
                    .version(1)
                    .status(OriginFlowDataStatusEnums.ENABLE.getStatus())
                    .createTime(System.currentTimeMillis() / 1000)
                    .build();
            if (version != null) {
                flowOriginDTO.setVersion(version + 1);
            }
            flowOriginDTO.setApiHash(hashCommonService.getApiHash(business, requestInfoDTO.getHost(), requestInfoDTO.getPath(),
                    source, flowOriginDTO.getVersion()));
            flowCollectionMapper.insertData(flowOriginDTO);
        } catch (Exception e) {
            log.error(PREFIX + "flowDataSave error:", e);
        }
    }

}
