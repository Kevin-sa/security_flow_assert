package com.kevinsa.security.service.enums;

public enum OperatorTypeEnums {
    UNKNOWN("unknown", "未知操作类型"),
    EQU("EQU", "equal || =="),
    NEQ("NEQ", "not equal || !="),
    MT("MT", "more than || >"),
    LT("LT", "less than || <"),
    ;

    private final String type;
    private final String desc;

    OperatorTypeEnums(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }
}
