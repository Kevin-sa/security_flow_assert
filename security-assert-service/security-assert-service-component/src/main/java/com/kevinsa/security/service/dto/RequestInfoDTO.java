package com.kevinsa.security.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfoDTO implements Serializable {
    private String uuid;
    private String host;
    private String path;
    private String method;
    Map<String, String> headers;
    @JsonProperty("payload")
    private String body;
}