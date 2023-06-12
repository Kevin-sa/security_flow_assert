package main.java.com.kevinsa.assertlog;

import burp.*;

import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender, IProxyListener {


    private IBurpExtenderCallbacks callbacks;
    private final static String ExtenderName = "sec_assert_log";
    private PrintWriter stdout;
    private PrintWriter stderr;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks iBurpExtenderCallbacks) {
        stdout = new PrintWriter(iBurpExtenderCallbacks.getStdout());
        stderr = new PrintWriter(iBurpExtenderCallbacks.getStderr());

        iBurpExtenderCallbacks.printOutput(ExtenderName);

        this.callbacks = iBurpExtenderCallbacks;
        callbacks.setExtensionName(ExtenderName);

        callbacks.registerProxyListener(BurpExtender.this);
    }

    @Override
    public void processProxyMessage(boolean b, IInterceptedProxyMessage iInterceptedProxyMessage) {
        IExtensionHelpers helpers = callbacks.getHelpers();
        IHttpRequestResponse iHttpRequestResponse = iInterceptedProxyMessage.getMessageInfo();
        if (b) {
            stdout.println("request");
            if (iHttpRequestResponse.getRequest() != null) {
                IRequestInfo iRequestInfo = helpers.analyzeRequest(iHttpRequestResponse.getRequest());
                String request = new String(iHttpRequestResponse.getRequest());
                stdout.println("request url" + iRequestInfo.getUrl());
            }
        } else {
            if (iHttpRequestResponse.getResponse() != null) {
                IResponseInfo iResponseInfo = helpers.analyzeResponse(iHttpRequestResponse.getResponse());
//                String response = new String(iHttpRequestResponse.getResponse());
                stdout.println("response status" + iResponseInfo.getStatusCode());

                byte[] responseBody = iHttpRequestResponse.getResponse();
                int bodyOffset = iResponseInfo.getBodyOffset();

                // 获取响应消息体
                byte[] responseBody = new byte[iHttpRequestResponse.getResponse().length - bodyOffset];
                System.arraycopy(iHttpRequestResponse.getResponse(), bodyOffset, responseBody, 0, responseBody.length);

                // 将消息体转换为字符串
                String responseBodyString = helpers.bytesToString(responseBody);

                // 处理响应消息体
                System.out.println("Response Body: " + responseBodyString);
            }
            stdout.println("response");
        }
    }
}


