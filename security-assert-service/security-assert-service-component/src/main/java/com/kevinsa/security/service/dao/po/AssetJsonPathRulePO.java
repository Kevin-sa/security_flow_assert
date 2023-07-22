package com.kevinsa.security.service.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetJsonPathRulePO {
    private Long id;
    private String business;
    private String apiHost;
    private String apiPath;
    private String data;
    private Integer type;
    private Integer status;
    private Long creatTime;
    private Long updateTime;
}
