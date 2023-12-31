package com.kevinsa.security.service.dao.po;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetJsonPathRuleDataPO {
    private String jsonPath;
    @JsonProperty("type")
    private String operatorType;
    @JsonProperty("value")
    private Object rightValue;
}
