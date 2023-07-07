package com.kevinsa.security.service.dao.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetJsonPathRuleDataDTO {
    private String jsonPath;
    private String operatorType;
    private Object rightValue;
}
