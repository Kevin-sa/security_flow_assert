package main.java.com.kevinsa.assertlog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class RequestInfoDTO {
    private String uuid;
    private String host;
    private String path;
    private String method;
    Map<String, String> headers;
    String payload;
}
