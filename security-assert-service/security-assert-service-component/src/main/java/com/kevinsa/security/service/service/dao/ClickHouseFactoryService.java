package com.kevinsa.security.service.service.dao;

import com.kevinsa.security.service.dao.po.FlowOriginPO;
import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;

public interface ClickHouseFactoryService {

    void pluginCollectEntrance(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, int statusId);

    void runnerAssertTaskEntrance(FlowOriginPO flowOriginPO, String body, int statusId);
}
