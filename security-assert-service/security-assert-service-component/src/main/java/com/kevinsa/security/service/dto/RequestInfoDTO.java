package com.kevinsa.security.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class RequestInfoDTO implements Serializable {
    private String uuid;
    private String host;
    private String path;
    private String method;
    Map<String, String> headers;
    private String body;
}