package com.kevinsa.security.service.service.bizDao;

import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;

public interface FlowDataDaoService {

    void flowDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, String business);
}
