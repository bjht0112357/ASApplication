package org.com.asapplication.tim;

import android.content.Context;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt;
import com.tencent.qcloud.uikit.BaseUIKitConfigs;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.business.session.model.SessionManager;
import com.tencent.qcloud.uikit.operation.UIKitMessageRevokedManager;

import java.util.List;

/**
 * Class Des:
 * Created by bjh on 2019/4/25.
 */
public class TimContanst {
    private static Context appContext;
    private static TIMSdkConfig config;

    public static void init(Context context, int sdkAppID, BaseUIKitConfigs configs) {
        appContext = context;
        config = new TIMSdkConfig(sdkAppID)
                .enableCrashReport(false)
                .enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG);
        initIM(context, sdkAppID);
//        System.out.println("TUIKIT>>>>>>>>>>>>>>>>>>" + (System.currentTimeMillis() - current));
//        current = System.currentTimeMillis();
//
//        BackgroundTasks.initInstance();
//        FileUtil.initPath();
//        System.out.println("TUIKIT>>>>>>>>>>>>>>>>>>" + (System.currentTimeMillis() - current));
//        current = System.currentTimeMillis();
//        FaceManager.loadFaceFiles();
//        System.out.println("TUIKIT>>>>>>>>>>>>>>>>>>" + (System.currentTimeMillis() - current));
//
//        SessionManager.getInstance().init();
//        C2CChatManager.getInstance().init();
//        GroupChatManager.getInstance().init();
    }
    private static void initIM(Context context, int sdkAppID) {
        if (config == null) {
            config = new TIMSdkConfig(sdkAppID)
                    .setLogLevel(TIMLogLevel.DEBUG);
            config.enableLogPrint(true);
        }
        TIMManager.getInstance().init(context, config);
        // 设置离线消息通知
        TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {

            @Override
            public void handleNotification(TIMOfflinePushNotification var1) {

            }
        });

        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
//                if (baseConfigs.getIMEventListener() != null) {
//                    baseConfigs.getIMEventListener().onForceOffline();
//                }
//                TUIKit.unInit();
            }

            @Override
            public void onUserSigExpired() {
//                if (baseConfigs.getIMEventListener() != null) {
//                    baseConfigs.getIMEventListener().onUserSigExpired();
//                }
//                TUIKit.unInit();
            }
        });

        userConfig.setConnectionListener(new TIMConnListener() {
            @Override
            public void onConnected() {
//                if (getBaseConfigs().getIMEventListener() != null)
//                    getBaseConfigs().getIMEventListener().onConnected();
            }

            @Override
            public void onDisconnected(int code, String desc) {
//                if (getBaseConfigs().getIMEventListener() != null)
//                    getBaseConfigs().getIMEventListener().onDisconnected(code, desc);
            }

            @Override
            public void onWifiNeedAuth(String name) {
//                if (getBaseConfigs().getIMEventListener() != null)
//                    getBaseConfigs().getIMEventListener().onWifiNeedAuth(name);
            }
        });

        userConfig.setRefreshListener(new TIMRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onRefreshConversation(List<TIMConversation> conversations) {
                SessionManager.getInstance().onRefreshConversation(conversations);
                if (TUIKit.getBaseConfigs().getIMEventListener() != null) {
                    TUIKit.getBaseConfigs().getIMEventListener().onRefreshConversation(conversations);
                }
            }
        });

        userConfig.setGroupEventListener(new TIMGroupEventListener() {
            @Override
            public void onGroupTipsEvent(TIMGroupTipsElem elem) {
//                if (TUIKit.getBaseConfigs().getIMEventListener() != null) {
//                    TUIKit.getBaseConfigs().getIMEventListener().onGroupTipsEvent(elem);
//                }
            }
        });

        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> msgs) {
//                if (TUIKit.getBaseConfigs().getIMEventListener() != null) {
//                    TUIKit.getBaseConfigs().getIMEventListener().onNewMessages(msgs);
//                }
                return false;
            }
        });

        TIMUserConfigMsgExt ext = new TIMUserConfigMsgExt(userConfig);
        ext.setMessageRevokedListener(UIKitMessageRevokedManager.getInstance());
        // 禁用自动上报，通过调用已读上报接口来实现已读功能
        ext.setAutoReportEnabled(false);
        TIMManager.getInstance().setUserConfig(ext);

    }
    public static void login(String userid, String usersig, final TIMCallBack callback) {
        TIMManager.getInstance().login(userid, usersig,callback);
    }
}
