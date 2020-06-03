package com.example.hime.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseFragment<P extends com.example.hime.base.BasePresenter> extends Fragment implements com.example.instantmessaging.base.BaseView {
    public P mPre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPre = initPre();
        if (mPre != null) {
            mPre.AttachView(this);
        }
//        或者在这里判断如果getLayoutID()   这个方法如果返回0   下面代码就不执行，就好了
//        懂了没
        View inflate = inflater.inflate(getLayoutID(), container, false);
        initView(inflate);
        initData();
        initListener();
        return inflate;

    }

    protected abstract P initPre();

    protected abstract int getLayoutID();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView(View view);
}
