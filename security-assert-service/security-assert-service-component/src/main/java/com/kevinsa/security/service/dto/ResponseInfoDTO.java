package com.kevinsa.security.service.dto;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseInfoDTO implements Serializable {
    private String uuid;
    private List<String> headers;
    private short statusCode;
    private String statedMimeType;
    private String body;
}
