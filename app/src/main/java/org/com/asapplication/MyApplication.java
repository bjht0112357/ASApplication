package org.com.asapplication;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FlowManager.init(this);
    }

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return  instance;
    }
}
