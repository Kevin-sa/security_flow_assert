package com.kevinsa.security.service.enums;

public enum ErrorCodeEnum {
    UNKNOWN(1000, "service error~"),
    PARAM_ERROR(1001, "param error~"),
    ;

    private final int code;
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
