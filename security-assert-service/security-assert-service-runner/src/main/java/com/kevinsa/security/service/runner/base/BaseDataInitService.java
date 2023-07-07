package com.kevinsa.security.service.runner.base;

import com.kevinsa.security.service.dao.dto.AssetJsonPathRuleDTO;
import com.kevinsa.security.service.dao.mapper.AssetActionRuleMapper;
import com.kevinsa.security.service.enums.AssertRuleTypeEnums;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseDataInitService implements ApplicationRunner {

    @Resource
    private AssetActionRuleMapper assetActionRuleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long defaultId = 0L;
        defaultId = assetActionRuleMapper.getIdByType(AssertRuleTypeEnums.DEFAULT.getTypeId());
        if (defaultId == 0L) {
            AssetJsonPathRuleDTO assetJsonPathRuleDTO = AssetJsonPathRuleDTO.builder()
                    .apiPath("*")
                    .apiHost("*")
                    .business("*")
                    .data("{}")
                    .status(1)
                    .type(AssertRuleTypeEnums.DEFAULT.getTypeId())
                    .creatTime(System.currentTimeMillis() / 1000)
                    .build();
            assetActionRuleMapper.insertRule(assetJsonPathRuleDTO);
        }
    }
}
