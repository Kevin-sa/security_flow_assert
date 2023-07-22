package com.kevinsa.security.service.enums;

public enum StateMachineStatusEnums {
    PLUGIN_REQUEST(1, "plugin update request info"),
    PLUGIN_RESPONSE( 2,"plugin update response info"),
    RUNNER_ASSERT_TASK(10, "assert task get flow info")
    ;

    private int statusId;
    private String desc;

    StateMachineStatusEnums(int statusId, String desc) {
        this.statusId = statusId;
        this.desc = desc;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
