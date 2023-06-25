package com.kevinsa.security.service.service.collect;

import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;

public interface CollectDataDaoService {

    void flowDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, String business);
}
