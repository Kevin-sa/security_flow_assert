package main.java.com.kevinsa.assertlog;

import burp.*;

import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BurpExtender implements IBurpExtender, IExtensionStateListener {
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
        callbacks.registerExtensionStateListener(BurpExtender.this);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(this::flowDataCollect, 60, TimeUnit.SECONDS);
    }

//    @Override
//    public void processHttpMessage(int i, boolean b, IHttpRequestResponse iHttpRequestResponse) {
//        if (i == IBurpExtenderCallbacks.TOOL_PROXY) {
//            if (b) {
//                IRequestInfo iRequestInfo = helpers.analyzeRequest(iHttpRequestResponse);
//                stdout.println(iRequestInfo);
//                IResponseInfo iResponseInfo = helpers.analyzeResponse(iHttpRequestResponse.getResponse());
//                stdout.println(iResponseInfo);
//            }
//        }
//    }

    @Override
    public void extensionUnloaded() {

    }

    private void flowDataCollect() {

    }
}
