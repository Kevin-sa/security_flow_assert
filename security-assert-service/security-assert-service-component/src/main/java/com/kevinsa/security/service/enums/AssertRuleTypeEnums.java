package com.kevinsa.security.service.enums;

public enum  AssertRuleTypeEnums {
    DEFAULT(0, "default rule"),
    REQUEST_SUCCESS(1, "request success check"),
    SECURITY_ASSET(2, "security asset check")
    ;
    private Integer typeId;
    private String desc;

    AssertRuleTypeEnums(Integer typeId, String desc) {
        this.typeId = typeId;
        this.desc = desc;
    }

    public Integer getTypeId() {
        return this.typeId;
    }
}
