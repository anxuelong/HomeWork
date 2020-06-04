package com.example.hime.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends com.example.hime.base.BasePresenter> extends AppCompatActivity implements com.example.instantmessaging.base.BaseView {
    public P mPre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPre = initPresenter();
        if (mPre != null) {
            mPre.AttachView(this);
        }
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutID();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPre != null){
            mPre.disAttachView();
        }
    }
}
