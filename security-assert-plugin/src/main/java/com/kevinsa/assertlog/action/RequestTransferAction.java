package main.java.com.kevinsa.assertlog.action;

import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IRequestInfo;
import main.java.com.kevinsa.assertlog.constant.PluginConfig;
import main.java.com.kevinsa.assertlog.dto.RequestInfoDTO;
import main.java.com.kevinsa.assertlog.utils.HttpClientUtils;
import main.java.com.kevinsa.assertlog.utils.ObjectMapperUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestTransferAction {

    private final HttpClientUtils httpClientUtils = new HttpClientUtils();

    public void executor(IHttpRequestResponse iHttpRequestResponse, IExtensionHelpers helpers, String uuid) {
        IRequestInfo iRequestInfo = helpers.analyzeRequest(iHttpRequestResponse.getRequest());
        if (iRequestInfo.getContentType() != IRequestInfo.CONTENT_TYPE_JSON && iRequestInfo.getContentType() != IRequestInfo.CONTENT_TYPE_NONE)
            return;
        RequestInfoDTO requestInfoDTO = transfer(iHttpRequestResponse, helpers, iRequestInfo);
        requestInfoDTO.setUuid(uuid);
        httpClientUtils.doPost(PluginConfig.SERVER_ADDRESS + PluginConfig.REQUEST_PATH, ObjectMapperUtils.toJSON(requestInfoDTO));
    }

    private RequestInfoDTO transfer(IHttpRequestResponse iHttpRequestResponse, IExtensionHelpers helpers, IRequestInfo iRequestInfo) {
        return RequestInfoDTO.builder()
                .host(iRequestInfo.getHeaders().get(1).replace("Host: ", ""))
                .path(getApiPath(iRequestInfo.getHeaders().get(0)))
                .method(iRequestInfo.getMethod())
                .headers(headerParser(iRequestInfo.getHeaders()))
                .payload(getReqPayload(iHttpRequestResponse, helpers, iRequestInfo))
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

    private String getReqPayload(IHttpRequestResponse iHttpRequestResponse, IExtensionHelpers helpers, IRequestInfo iRequestInfo) {
        byte[] requestBytes = iHttpRequestResponse.getRequest();
        byte[] requestBody = new byte[requestBytes.length - iRequestInfo.getBodyOffset()];
        System.arraycopy(requestBytes, iRequestInfo.getBodyOffset(), requestBody, 0, requestBody.length);
//        return helpers.bytesToString(requestBody);
        return new String(requestBody, StandardCharsets.UTF_8);
    }


    private String getApiPath(String headerLine) {
        String[] tmp = headerLine.split(" ");
        if (tmp.length == 3) {
            return tmp[1];
        }
        return null;
    }
}
