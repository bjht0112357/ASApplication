package org.com.asapplication;

import android.app.Application;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return  instance;
    }

}
