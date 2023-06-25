package main.java.com.kevinsa.assertlog.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseInfoDTO {
    private String uuid;
    private List<String> headers;
    private short statusCode;
    private String statedMimeType;
    private String body;
}
