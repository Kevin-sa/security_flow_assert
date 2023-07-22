package com.kevinsa.security.service.runner.dao;

import com.kevinsa.security.service.dao.clickhouse.ClickHouseFactory;
import com.kevinsa.security.service.dao.po.ClickHouseBaseInfoPO;
import com.kevinsa.security.service.dao.po.ClickHouseExtendsInfoPO;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.runner.TaskApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = TaskApplication.class)
@RunWith(SpringRunner.class)
public class ClickHouseServiceTest {

    @Autowired
    private ClickHouseFactory clickHouseFactory;

    @Test
    public void clickHouseInsert() {
        RequestInfoDTO requestInfoDTO = RequestInfoDTO.builder()
                .body("{\"name\":\"kevinsa\"}")
                .host("www.baidu.com")
                .path("/a/a/b/bb")
                .build();

        ResponseInfoDTO responseInfoDTO = ResponseInfoDTO.builder()
                .body("{\"name\":\"kevinsa\"}")
                .build();
        ClickHouseExtendsInfoPO clickHouseExtendsInfoPO = ClickHouseExtendsInfoPO.builder()
                .request(requestInfoDTO)
                .response(responseInfoDTO)
                .build();

        ClickHouseBaseInfoPO clickHouseBaseInfoPO = ClickHouseBaseInfoPO.builder()
                .statusId(1)
                .build();
        clickHouseFactory.insertFlowRecord(clickHouseBaseInfoPO, clickHouseExtendsInfoPO);
    }
}
