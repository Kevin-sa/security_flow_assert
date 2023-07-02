package com.kevinsa.security.service.service.task;

import com.kevinsa.security.service.dao.dto.FlowOriginDTO;
import com.kevinsa.security.service.dao.mapper.FlowCollectionMapper;
import com.kevinsa.security.service.enums.OriginFlowDataStatusEnums;
import com.kevinsa.security.service.service.assertCheck.AssertExecutorFactory;
import com.kevinsa.security.service.service.assertCheck.base.AssertProcessFlow;
import com.kevinsa.security.service.service.assertCheck.base.AssertStepAction;
import com.kevinsa.security.service.service.assertCheck.context.DefaultProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ExampleAssertTaskServer {

    @Autowired
    private AssertExecutorFactory assertExecutorFactory;

    @Resource
    private AssertProcessFlow processFlow;

    @Resource
    private FlowCollectionMapper flowCollectionMapper;


    public Runnable getRunnableExec(String bizMsg) {
        return () -> {
            try {
                DefaultProcessContext context = assertExecutorFactory.createDefaultContext();
                context.setBizMsg(bizMsg);
                List<String> distinctHosts = flowCollectionMapper.getDistinctHostByBiz(bizMsg,
                        OriginFlowDataStatusEnums.ENABLE.getStatus());
                for (String host : distinctHosts) {
                    List<String> distinctPaths = flowCollectionMapper.getDistinctPathByBiz(bizMsg,
                            OriginFlowDataStatusEnums.ENABLE.getStatus(), host);
                    distinctPaths.forEach(path -> {
                        FlowOriginDTO flowOriginDTO = flowCollectionMapper.getInfoByBizAndPath(bizMsg,
                                host, path, OriginFlowDataStatusEnums.ENABLE.getStatus());
                        if (flowOriginDTO != null) {
                            context.setBizMsg(bizMsg);
                            context.setFlowOriginDTO(flowOriginDTO);
                            try {
                                processFlow.process(context);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        context.remove();
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
