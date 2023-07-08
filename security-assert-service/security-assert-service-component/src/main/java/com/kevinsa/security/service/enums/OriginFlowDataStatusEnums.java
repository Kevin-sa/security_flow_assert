package com.kevinsa.security.service.enums;

public enum  OriginFlowDataStatusEnums {
    DISABLE(0, "disable"),
    ENABLE(1, "enable"),
    ;

    private Integer status;
    private String desc;

    OriginFlowDataStatusEnums(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }
}
