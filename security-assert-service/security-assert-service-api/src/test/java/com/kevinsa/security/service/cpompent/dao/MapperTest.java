package com.kevinsa.security.service.cpompent.dao;


import com.kevinsa.security.service.Application;
import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class MapperTest {

    @Resource
    private FlowCollectionMapper flowCollectionMapper;

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
}
