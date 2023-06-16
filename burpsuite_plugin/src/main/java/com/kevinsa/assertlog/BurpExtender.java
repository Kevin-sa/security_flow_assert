package main.java.com.kevinsa.assertlog;

import burp.*;
import main.java.com.kevinsa.assertlog.action.RequestTransferAction;
import main.java.com.kevinsa.assertlog.action.ResponseTransferAction;

import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.*;

public class BurpExtender implements IBurpExtender, IProxyListener {

    private IBurpExtenderCallbacks CALLBACKS;
    private final static String EXTENDER_NAME = "sec_assert_log";
    private PrintWriter stdout;
    private PrintWriter stderr;
    private static String BURPSUITE_TASK_UUID = "";

    private ThreadPoolExecutor renderThreadPool;
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 20;
    private static final int KEEP_ALIVE = 60;

    private final RequestTransferAction requestTransferAction = new RequestTransferAction();
    private final ResponseTransferAction responseTransferAction = new ResponseTransferAction();

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks iBurpExtenderCallbacks) {
        BURPSUITE_TASK_UUID = UUID.randomUUID().toString() + "_";

        stdout = new PrintWriter(iBurpExtenderCallbacks.getStdout());
        stderr = new PrintWriter(iBurpExtenderCallbacks.getStderr());

        iBurpExtenderCallbacks.printOutput(EXTENDER_NAME);
        this.CALLBACKS = iBurpExtenderCallbacks;
        CALLBACKS.setExtensionName(EXTENDER_NAME);

        CALLBACKS.registerProxyListener(BurpExtender.this);

        renderThreadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory()
        );
    }

    /**
     * get HTTP message and send it to the server using a thread pool
     * a guarantee of atomicity:messageReference
     */
    @Override
    public void processProxyMessage(boolean b, IInterceptedProxyMessage iInterceptedProxyMessage) {
        IExtensionHelpers helpers = CALLBACKS.getHelpers();
        IHttpRequestResponse iHttpRequestResponse = iInterceptedProxyMessage.getMessageInfo();
        if (b) {
            if (iHttpRequestResponse.getRequest() != null) {
                IRequestInfo iRequestInfo = helpers.analyzeRequest(iHttpRequestResponse.getRequest());
                renderThreadPool.execute(() -> requestTransferAction.executor(iRequestInfo, BURPSUITE_TASK_UUID));
            }
        } else {
            if (iHttpRequestResponse.getResponse() != null) {
                IResponseInfo iResponseInfo = helpers.analyzeResponse(iHttpRequestResponse.getResponse());
                renderThreadPool.execute(() -> responseTransferAction.executor(iResponseInfo, BURPSUITE_TASK_UUID));
            }
        }
    }

}


