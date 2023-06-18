package main.java.com.kevinsa.assertlog.action;

import burp.IRequestInfo;
import main.java.com.kevinsa.assertlog.dto.RequestInfoDTO;
import main.java.com.kevinsa.assertlog.utils.HttpClientUtils;
import main.java.com.kevinsa.assertlog.utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestTransferAction {

    private final HttpClientUtils httpClientUtils = new HttpClientUtils();

//                String request = new String(iHttpRequestResponse.getRequest());
//                stdout.println("request url" + iRequestInfo.getUrl());

    public void executor(IRequestInfo iRequestInfo, String uuid) {
        RequestInfoDTO requestInfoDTO = transfer(iRequestInfo);
        requestInfoDTO.setUuid(uuid);
        httpClientUtils.doPost("http://127.0.0.1:8088", ObjectMapperUtils.toJSON(requestInfoDTO));
    }

    private RequestInfoDTO transfer(IRequestInfo iRequestInfo) {
        return RequestInfoDTO.builder()
                .host(iRequestInfo.getHeaders().get(1))
                .path(getApiPath(iRequestInfo.getHeaders().get(0)))
                .method(iRequestInfo.getMethod())
                .headers(headerParser(iRequestInfo.getHeaders()))
                .build();
    }

    private Map<String, String> headerParser(List<String> requestInfoHeaders) {
        Map<String, String> headers = new HashMap<>();
        for (int i = 2; i < requestInfoHeaders.size(); i++) {
            String[] infos = requestInfoHeaders.get(i).split(":");
            if (infos.length != 2) {
                break;
            }
            headers.put(infos[0], infos[1].trim());
        }
        return headers;
    }


    private List<String> getHeaders(IRequestInfo iRequestInfo) {
        return iRequestInfo.getHeaders();
    }


    private String getApiPath(String headerLine) {
        String[] tmp = headerLine.split(" ");
        if (tmp.length == 3) {
            return tmp[1];
        }
        return null;
    }
}
