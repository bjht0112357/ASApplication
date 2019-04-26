package org.com.asapplication;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.session.SessionWrapper;

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
//        TUIKit.init(this,	1400202492, BaseUIKitConfigs.getDefaultConfigs());
        //初始化 SDK 基本配置
//判断是否是在主线程
        if (SessionWrapper.isMainProcess(getApplicationContext())) {
            TIMSdkConfig config = new TIMSdkConfig(1400202492)
                    .enableCrashReport(false)
                    .enableLogPrint(true)
                    .setLogLevel(TIMLogLevel.DEBUG);

            //初始化 SDK
            TIMManager.getInstance().init(getApplicationContext(), config);
        }

    }

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return  instance;
    }
}
