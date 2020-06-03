package com.example.hime.app;

import android.app.Activity;
import android.os.Process;

import androidx.fragment.app.Fragment;

import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack = new Stack<>();
    private static Stack<Fragment> fragmentStack = new Stack<>();
    private static volatile AppManager appManager;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null) {
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }

    /**
     * 获取所有activity
     *
     * @return
     */
    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 获取所有Fragment
     *
     * @return
     */
    public static Stack<Fragment> getFragmentStack() {
        return fragmentStack;
    }

    /**
     * A  B C D EF
     * 先进后出
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除指定Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
        }
    }

    /**
     * 删除当前Activity
     */
    public void deleteActivity() {
        Activity activity = activityStack.lastElement();//获取栈中最后一个
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public void appExit(){
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            activity.finish();
        }
        activityStack.clear();
        System.exit(0);//退出程序   java
        Process.killProcess(Process.myPid());//关闭线程   android
    }
}
