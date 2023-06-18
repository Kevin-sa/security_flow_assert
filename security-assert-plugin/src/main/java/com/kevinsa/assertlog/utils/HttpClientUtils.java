package main.java.com.kevinsa.assertlog.utils;

import okhttp3.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {

    private static final String prefix = "HttpClientUtils execute ->";

    private static final int TIMEOUT = 30000;

    public String doGet(String requestUrl, Map<String, String> headers) {
        Headers setHeaders = buildHeaders(headers);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(requestUrl)
                .headers(setHeaders)
                .build();
        return doCall(client, request);
    }

    public void doPost(String requestUrl, String data) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(requestBody)
                .build();
        doCall(client, request);
    }

    public String doPost(String requestUrl, Map<String, String> headers, String data) {
        Headers setHeaders = buildHeaders(headers);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(requestUrl)
                .headers(setHeaders)
                .post(requestBody)
                .build();
        return doCall(client, request);
    }

    private String doCall(OkHttpClient client, Request request) {
        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            if (response.body() != null) {
                return response.body().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Headers buildHeaders(Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers.size() > 0) {
            for (String key : headers.keySet()) {
                headerBuilder.add(key, headers.get(key));
            }
        }
        return headerBuilder.build();
    }
}
