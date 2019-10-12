package com.example.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * @author lhf
 * @create 2019/10/12
 * @Describe activity 类的管理
 */
public class ActivityStackManager {
    private static Stack<Activity> activityStack;

    //内部类实现单例模式,延迟加载，减少内存开销
    private static class ActivityStackManagerHolder {
        private static ActivityStackManager instance = new ActivityStackManager();
    }

    //私有的构造函数
    private ActivityStackManager() {
    }

    public static ActivityStackManager getInstance() {
        return ActivityStackManagerHolder.instance;
    }

    /**
     * 将Activity 添加到ActivityStackManager 中
     *
     * @param mActivity
     */
    public void pushActivity(Activity mActivity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(mActivity);

    }

    /**
     * 移除所有的Activity
     */
    public void popAllActivity() {
        if (activityStack == null) return;
        while (true) {
            if (activityStack.isEmpty()) break;
            Activity activity = currentActivity();
            popActivity(activity);
        }


    }

    /**
     * 关闭当前Activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        if (activityStack == null) return;
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(0, 0);
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) return null;
        Activity activity = (Activity) activityStack.lastElement();
        return activity;
    }

    /**
     * 获取最后一个的Activity
     *
     * @return
     */
    public Activity firstActivity() {
        if (activityStack == null || activityStack.isEmpty()) return null;
        Activity activity = (Activity) activityStack.firstElement();
        return activity;
    }
}
