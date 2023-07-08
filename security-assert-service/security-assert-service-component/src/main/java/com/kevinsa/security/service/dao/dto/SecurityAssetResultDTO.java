package com.kevinsa.security.service.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecurityAssetResultDTO {
    private Long id;
    private Long ruleId;
    private Long flowId;
    private Long replayFlowId;
    private String responseBody;
    private String diffValue;
    private Long createTime;
}
