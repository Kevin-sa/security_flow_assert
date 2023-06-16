package main.java.com.kevinsa.assertlog.action;

import burp.IRequestInfo;
import main.java.com.kevinsa.assertlog.param.RequestInfoPO;

public class RequestTransferAction {

    public void executor(IRequestInfo iRequestInfo, String uuid) {

    }

    private RequestInfoPO transfer(IRequestInfo iRequestInfo) {
        //                String url = "";
//                try {
//                    url = iRequestInfo.getUrl().toString();
//                } catch (Exception e) {
//                    if (iRequestInfo.getHeaders().size() > 0) {
//                        url = getApiPath(iRequestInfo.getHeaders().get(0));
//                    }
//                }
//                String request = new String(iHttpRequestResponse.getRequest());
//                stdout.println("request url" + iRequestInfo.getUrl());
        return RequestInfoPO.builder().build();
    }

    private void requestExecutor(IRequestInfo iRequestInfo) {
        String url = getApiPath(iRequestInfo.getHeaders().get(0));


    }

    private String getApiPath(String headerFirstLine) {
        String[] tmp = headerFirstLine.split(" ");
        if (tmp.length == 3) {
            return tmp[1];
        }
        return null;
    }
}
