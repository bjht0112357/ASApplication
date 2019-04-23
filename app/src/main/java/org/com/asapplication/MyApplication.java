package org.com.asapplication;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.RefWatcher;

import org.litepal.LitePal;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */
public class MyApplication extends Application {
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FlowManager.init(this);
        LitePal.initialize(this);
        //暂时有坑
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return  instance;
    }
}
