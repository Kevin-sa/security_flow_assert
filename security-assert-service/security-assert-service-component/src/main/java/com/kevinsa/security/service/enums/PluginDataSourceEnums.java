package com.kevinsa.security.service.enums;

public enum PluginDataSourceEnums {
    UNKNOWN(0, "unknown"),
    BURPSUITE(1, "burp suite plugin"),
    ;

    private Integer source;
    private String desc;

    PluginDataSourceEnums(Integer source, String desc) {
        this.source = source;
        this.desc = desc;
    }

    public Integer getSource() {
        return source;
    }

}
