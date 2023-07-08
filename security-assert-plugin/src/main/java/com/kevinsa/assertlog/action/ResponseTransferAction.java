package main.java.com.kevinsa.assertlog.action;

import java.nio.charset.StandardCharsets;

import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IResponseInfo;
import main.java.com.kevinsa.assertlog.constant.PluginConfig;
import main.java.com.kevinsa.assertlog.dto.ResponseInfoDTO;
import main.java.com.kevinsa.assertlog.utils.HttpClientUtils;
import main.java.com.kevinsa.assertlog.utils.ObjectMapperUtils;

public class ResponseTransferAction {

    private final HttpClientUtils httpClientUtils = new HttpClientUtils();

    public void executor(IHttpRequestResponse iHttpRequestResponse, IExtensionHelpers helpers, String uuid) {
        IResponseInfo iResponseInfo = helpers.analyzeResponse(iHttpRequestResponse.getResponse());
        if (!iResponseInfo.getStatedMimeType().equals("JSON")) return;
        ResponseInfoDTO responseInfoDTO = transfer(iHttpRequestResponse, helpers, iResponseInfo);
        responseInfoDTO.setUuid(uuid);
        httpClientUtils.doPost(PluginConfig.SERVER_ADDRESS + PluginConfig.RESPONSE_PATH, ObjectMapperUtils.toJSON(responseInfoDTO));
    }

    public ResponseInfoDTO transfer(IHttpRequestResponse iHttpRequestResponse, IExtensionHelpers helpers, IResponseInfo iResponseInfo) {
        return ResponseInfoDTO.builder()
                .statusCode(iResponseInfo.getStatusCode())
                .body(getBody(iHttpRequestResponse, helpers, iResponseInfo))
                .headers(iResponseInfo.getHeaders())
                .statusCode(iResponseInfo.getStatusCode())
                .statedMimeType(iResponseInfo.getStatedMimeType())
                .build();
    }

    private String getBody(IHttpRequestResponse iHttpRequestResponse, IExtensionHelpers helpers, IResponseInfo iResponseInfo) {
        byte[] responseBytes = iHttpRequestResponse.getResponse();
        byte[] responseBody = new byte[responseBytes.length - iResponseInfo.getBodyOffset()];
        System.arraycopy(responseBytes, iResponseInfo.getBodyOffset(), responseBody, 0, responseBody.length);
        return new String(responseBody, StandardCharsets.UTF_8);
    }
}
