package com.kevinsa.security.service.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClickHouseBaseInfoPO {
    private Integer statusId;
    private String host;
    private String path;
    private String uuid;
}
