package com.example.hime.base;

public class BasePresenter<V extends com.example.hime.base.BaseView> {
    public V mView;

    public void AttachView(V baseView) {
        this.mView = baseView;
    }

    public void disAttachView(){
        this.mView = null;
    }

}
