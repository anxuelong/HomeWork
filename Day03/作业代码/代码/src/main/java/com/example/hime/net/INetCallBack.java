package com.example.hime.net;

public interface INetCallBack<T> {
    void onSuccess(T t);
    void OnError(Throwable throwable);
}
