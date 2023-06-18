package main.java.com.kevinsa.assertlog.action;

import burp.IResponseInfo;
import main.java.com.kevinsa.assertlog.dto.ResponseInfoDTO;

public class ResponseTransferAction {

    public void executor(IResponseInfo iResponseInfo, String uuid) {

    }

    public ResponseInfoDTO transfer() {
        //                String response = new String(iHttpRequestResponse.getResponse());
//        stdout.println("response status" + iResponseInfo.getStatusCode());

//                byte[] responseBody = iHttpRequestResponse.getResponse();
//                int bodyOffset = iResponseInfo.getBodyOffset();
//
//                // 获取响应消息体
//                byte[] responseBody = new byte[iHttpRequestResponse.getResponse().length - bodyOffset];
//                System.arraycopy(iHttpRequestResponse.getResponse(), bodyOffset, responseBody, 0, responseBody.length);
//
//                // 将消息体转换为字符串
//                String responseBodyString = helpers.bytesToString(responseBody);
//
//                // 处理响应消息体
//                System.out.println("Response Body: " + responseBodyString);
        return ResponseInfoDTO.builder().build();
    }
}
