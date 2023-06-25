package main.java.com.kevinsa.assertlog;

import burp.*;
import main.java.com.kevinsa.assertlog.action.RequestTransferAction;
import main.java.com.kevinsa.assertlog.action.ResponseTransferAction;
import main.java.com.kevinsa.assertlog.action.UnloadAction;

import java.util.UUID;
import java.util.concurrent.*;

public class BurpExtender implements IBurpExtender, IProxyListener {

    private final static String EXTENDER_NAME = "sec_assert_log";
    private static String BURPSUITE_TASK_UUID = "";

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 20;
    private static final int KEEP_ALIVE = 60;

    private ThreadPoolExecutor renderThreadPool;
    private IBurpExtenderCallbacks callbacks;

    private final RequestTransferAction requestTransferAction = new RequestTransferAction();
    private final ResponseTransferAction responseTransferAction = new ResponseTransferAction();

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks iBurpExtenderCallbacks) {
        BURPSUITE_TASK_UUID = UUID.randomUUID().toString() + "_";

        this.callbacks = iBurpExtenderCallbacks;
        callbacks.setExtensionName(EXTENDER_NAME);

        callbacks.registerProxyListener(BurpExtender.this);
        callbacks.registerExtensionStateListener(new UnloadHandler());

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
        IExtensionHelpers helpers = callbacks.getHelpers();
        IHttpRequestResponse iHttpRequestResponse = iInterceptedProxyMessage.getMessageInfo();
        String uuid = BURPSUITE_TASK_UUID + iInterceptedProxyMessage.getMessageReference();
        if (b) {
            if (iHttpRequestResponse.getRequest() != null) {
                renderThreadPool.execute(() -> {
                    try {
                        requestTransferAction.executor(iHttpRequestResponse, helpers, uuid);
                    } catch (Exception e) {
                        callbacks.printError("requestTransferAction error:" + e.getMessage());
                    }
                });
            }
        } else {
            if (iHttpRequestResponse.getResponse() != null) {
                renderThreadPool.execute(() -> {
                    try {
                        responseTransferAction.executor(iHttpRequestResponse, helpers, uuid);
                    } catch (Exception e) {
                        callbacks.printError("responseTransferAction error:" + e.getMessage());
                    }
                });
            }
        }
    }


    private static class UnloadHandler implements IExtensionStateListener {
        public void extensionUnloaded() {
            UnloadAction unloadAction = new UnloadAction();
            unloadAction.executor(BURPSUITE_TASK_UUID);
        }
    }
}


