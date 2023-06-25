package com.kevinsa.security.service.cpompent;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kevinsa.security.service.Application;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.service.collect.BaseExecutor;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ActionTest {

    @Autowired
    private BaseExecutor baseExecutor;

    @Test
    public void requestExecuteTest() {
        RequestInfoDTO requestInfoDTO = RequestInfoDTO.builder()
                .host("s.kwaixiaodian.com")
                .path("/passort/auth")
                .uuid(UUID.randomUUID().toString())
                .build();
        baseExecutor.requestExecute(requestInfoDTO);
    }
}
