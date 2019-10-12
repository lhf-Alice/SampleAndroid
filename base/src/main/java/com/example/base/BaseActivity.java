package com.example.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.manager.ActivityStackManager;

/**
 * @author lhf
 * @create 2019/10/11
 * @Describe 基类activity
 */
public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().pushActivity(this);
        mContext = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().popActivity(this);
    }

    //退出所有app
    public void exitApp() {
        ActivityStackManager.getInstance().popAllActivity();
        System.exit(0);
    }
}
