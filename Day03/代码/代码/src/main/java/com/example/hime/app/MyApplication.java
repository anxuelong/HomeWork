package com.example.hime.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.squareup.leakcanary.LeakCanary;

public class MyApplication extends Application {
    public static MyApplication myApplication;
    public static boolean IsSortInfo = false;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //初始化内存泄漏检测工具
//        initLeakCanary();
        //注册Activity生命周期的接口
        //注册监听每个Activity的生命周期，便于栈管理
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /**
     * Activity生命周期的接口回调
     */
    ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            com.example.hime.app.AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            com.example.hime.app.AppManager.getInstance().removeActivity(activity);
        }
    };

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
}
