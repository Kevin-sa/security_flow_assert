package com.kevinsa.security.service.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseInfoDTO implements Serializable {
    private String uuid;
    private String body;
}
