package com.kevinsa.security.service.cpompent.dao;


import com.kevinsa.security.service.Application;
import com.kevinsa.security.service.dao.dto.AssetJsonPathRuleDTO;
import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.AssetActionRuleMapper;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.utils.HashUtils;
import com.kevinsa.security.service.utils.JsonHierarchyParseUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static com.kevinsa.security.service.enums.AssertRuleTypeEnums.REQUEST_SUCCESS;
import static com.kevinsa.security.service.enums.OriginFlowDataStatusEnums.ENABLE;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class MapperTest {

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

    @Autowired
    private JsonHierarchyParseUtils jsonHierarchyParseUtils;

    @Autowired
    private HashUtils hashUtils;

    @Resource
    private AssetActionRuleMapper assetActionRuleMapper;


    @Test
    public void originDataInsertTest() {
        FlowOriginDTO flowOriginDTO = FlowOriginDTO.builder()
                .apiHost("kevinsa.com")
                .apiPath("/aaa/aaa")
                .business("kevinsa-com")
                .createTime(System.currentTimeMillis() / 1000)
                .dataSource(1)
                .status(0)
                .build();
        flowCollectionMapper.insertData(flowOriginDTO);
    }

    @Test
    public void selectMaxVersionByApiInfoTest() {
        Integer version = flowCollectionMapper.selectMaxVersionByApiInfo("kevinsa-com",
                "kevinsa.com", "/aaa/aaa", 1);
        System.out.println(version);
    }

    @Test
    public void hashCompare() {
        List<FlowOriginDTO> results = flowCollectionMapper.getInfoByBizAndPaths();
        for (FlowOriginDTO flowOriginDTO : results) {
            List<String> respJsonTree = jsonHierarchyParseUtils.getJsonKey(flowOriginDTO.getResponseBody(), "", 1);
            System.out.println(getJsonTreeHash(flowOriginDTO.getBusiness(), flowOriginDTO.getApiHost(), flowOriginDTO.getApiPath(), flowOriginDTO.getDataSource(), respJsonTree));
        }

    }

    private String getJsonTreeHash(String business, String apiHost, String apiPath, int dataSource,
                                   List<String> jsonTreeList) {
        String message = business + "|" + apiHost + "|" + apiPath + "|" + dataSource + "|"
                + String.join(",", jsonTreeList);
        System.out.println(message);
        return hashUtils.md5(message);
    }

    @Test
    public void assetJsonPathRuleMapperTest() {
        AssetJsonPathRuleDTO reqSuccessRuleDTO = assetActionRuleMapper.getRuleByTypeAndApiInfo(
                ENABLE.getStatus(), REQUEST_SUCCESS.getTypeId(), "", "", ""
        );
        System.out.println(reqSuccessRuleDTO == null);
    }
}
