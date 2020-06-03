package com.example.hime.net;

import java.util.HashMap;

public class HttpUrlConnectionUtils implements com.example.hime.net.INetWork {
    public static volatile HttpUrlConnectionUtils httpUrlConnectionUtils;

    private HttpUrlConnectionUtils() {
    }

    public static HttpUrlConnectionUtils getInstance() {
        if (httpUrlConnectionUtils == null){
            synchronized (HttpUrlConnectionUtils.class){
                if (httpUrlConnectionUtils == null){
                    httpUrlConnectionUtils = new HttpUrlConnectionUtils();
                }
            }
        }
        return httpUrlConnectionUtils;
    }

    @Override
    public <T> void get(String url, com.example.instantmessaging.net.INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void get(String url, HashMap<String, String> hashMap, com.example.instantmessaging.net.INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, com.example.instantmessaging.net.INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> hashMap, com.example.instantmessaging.net.INetCallBack<T> netCallBack) {

    }
}
