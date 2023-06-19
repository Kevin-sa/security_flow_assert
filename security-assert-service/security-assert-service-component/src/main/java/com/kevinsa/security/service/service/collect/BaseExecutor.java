package com.kevinsa.security.service.service.collect;


import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.service.collect.base.ProcessContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Lazy
@Service
public class BaseExecutor {

    private static final String PREFIX = "BaseExecutor ->";

    private static final Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    private Map<String, String> flowUuidData = new ConcurrentHashMap<String, String>();


    public void requestExecute(RequestInfoDTO requestInfoDTO) {
        try {
            ProcessContext<RequestInfoDTO> processContext = new ProcessContext<RequestInfoDTO>();
            processContext.setData(requestInfoDTO);
        } catch (Exception e) {
            logger.error(PREFIX + "requestExecute error:", e);
        }

    }
}
