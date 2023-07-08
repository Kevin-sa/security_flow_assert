package com.kevinsa.security.service.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Response<T>{
    public static final int SUCCESS = 1;

    private int result = SUCCESS;

    @JsonProperty("error_msg")
    private String errorMsg;

    private T data;

    public Response() {
    }

    public Response(int errorCode, String errorMessage) {
        this.result = errorCode;
        this.errorMsg = errorMessage;
    }

    public Response(T data) {
        this.data = data;
    }
}

