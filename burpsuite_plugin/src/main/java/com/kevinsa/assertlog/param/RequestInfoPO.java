package main.java.com.kevinsa.assertlog.param;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestInfoPO {
    private String host;
    private String path;
    private String method;
    List<String> headers;
}
