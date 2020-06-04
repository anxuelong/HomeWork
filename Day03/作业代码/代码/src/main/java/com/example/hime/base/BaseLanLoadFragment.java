package com.example.hime.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLanLoadFragment<P extends com.example.hime.base.BasePresenter> extends Fragment implements com.example.instantmessaging.base.BaseView {
    public P mPre;
    public boolean isDataLoaded = false;//请求数据
    private boolean isViewCreated = false;//页面创建

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutID(), container, false);
        isViewCreated = true;
        mPre = initPre();
      if (null!= mPre){
          mPre.AttachView(this);
      }
        initView(inflate);
        initListener();
        return inflate;
    }

    private void lazyLoad(){
//        true可见    1、当前页面是否可见   2、当前页面是否已经加载   3、当前页面数据是否已经加载
        if( getUserVisibleHint()  && isViewCreated  && !isDataLoaded){
//           加载数据
            initData();
            isDataLoaded = true;
        }else{
//           不需要加载数据
        }
    }

    protected abstract P initPre();

    //    获取焦点
    @Override
    public void onResume() {
        super.onResume();
        isCurrentVisibleToUser(true);
    }
    //    暂停--失去焦点
    @Override
    public void onPause() {
        super.onPause();
        isCurrentVisibleToUser(false);
    }

    //点击home按键时，这个方法不会被执行
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        true/false   可见或者不可见
        lazyLoad();
        Log.e("TAG","当前Fragment是否是可见状态");
        isCurrentVisibleToUser(isVisibleToUser);
    }


    protected abstract void isCurrentVisibleToUser(boolean isVisibleToUser);

    protected abstract int getLayoutID();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView(View view);
}
