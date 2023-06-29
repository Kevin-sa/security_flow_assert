package com.kevinsa.security.service.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlowOriginDTO {
    private Long id;
    private String business;
    private String apiHost;
    private String apiPath;
    private String headersInfo;
    private String requestPayload;
    private String requestJsonTree;
    private String requestJsonTreeHash;
    private String responseBody;
    private String responseJsonTree;
    private String responseJsonTreeHash;
    private Integer status;
    private Integer dataSource;
    private Integer version;
    private String apiHash;
    private Long createTime;
}
