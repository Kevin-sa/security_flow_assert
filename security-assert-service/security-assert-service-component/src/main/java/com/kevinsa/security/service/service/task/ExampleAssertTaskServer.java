package com.kevinsa.security.service.service.task;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.enums.OriginFlowDataStatusEnums;
import com.kevinsa.security.service.service.assertCheck.AssertExecutorFactory;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessContext;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ExampleAssertTaskServer {

    @Autowired
    private AssertExecutorFactory assertExecutorFactory;

    @Autowired
    private AssertStepAction assertStepAction;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;


    public Runnable getRunnableExec(String bizMsg) {
        return () -> {
            try {
                AssertProcessContext assertProcessContext = assertExecutorFactory.createDefaultContext();
                List<String> distinctHosts = flowCollectionMapper.getDistinctHostByBiz(bizMsg,
                        OriginFlowDataStatusEnums.ENABLE.getStatus());
                for (String host : distinctHosts) {
                    List<String> distinctPaths = flowCollectionMapper.getDistinctPathByBiz(bizMsg,
                            OriginFlowDataStatusEnums.ENABLE.getStatus(), host);
                    distinctPaths.forEach(path -> {
                        FlowOriginDTO flowOriginDTO = flowCollectionMapper.getInfoByBizAndPath(bizMsg,
                                host, path, OriginFlowDataStatusEnums.ENABLE.getStatus());
                        if (flowOriginDTO != null) {
                            assertProcessContext.setBizId(bizMsg);
                            assertProcessContext.setFlowData(flowOriginDTO);
                            assertStepAction.process(assertProcessContext);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
