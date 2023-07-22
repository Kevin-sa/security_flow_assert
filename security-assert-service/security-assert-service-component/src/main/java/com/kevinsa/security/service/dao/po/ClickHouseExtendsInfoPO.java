package com.kevinsa.security.service.dao.po;

import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClickHouseExtendsInfoPO {
    private RequestInfoDTO request;
    private ResponseInfoDTO response;
}
