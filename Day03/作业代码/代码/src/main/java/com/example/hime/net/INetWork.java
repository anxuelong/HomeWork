package com.example.hime.net;

import java.util.HashMap;

public interface INetWork {
    //get
    //post
    <T> void get(String url, com.example.hime.net.INetCallBack<T> netCallBack);
    <T> void get(String url, HashMap<String, String> hashMap, com.example.hime.net.INetCallBack<T> netCallBack);

    <T> void post(String url, com.example.hime.net.INetCallBack<T> netCallBack);
    <T> void post(String url, HashMap<String, String> hashMap, com.example.hime.net.INetCallBack<T> netCallBack);
}
