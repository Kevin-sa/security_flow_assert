package com.kevinsa.security.service.enums;

public enum  JsonPathTypeEnums {
    REQUEST_SUCCESS(1, "request success check"),
    SECURITY_ASSET(2, "security asset check")
    ;
    private Integer typeId;
    private String desc;

    JsonPathTypeEnums(Integer typeId, String desc) {
        this.typeId = typeId;
        this.desc = desc;
    }

    public Integer getTypeId() {
        return this.typeId;
    }
}
