package org.com.asapplication.rxjava.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.com.asapplication.MyApplication;

import rx.Subscriber;

/**
 * Class Des:自定义Subscriber捕获异常及返回方法封装{@link #onError}，{@link #onResponse}
 * Created by bjh on 2018/3/19.
 */

public abstract class ResponseSubscriber<T> extends Subscriber<T> {

    public ResponseSubscriber() {
    }

    @Override
    public void onStart() {
        super.onStart();
        //接下来可以检查网络连接等操作

        if (!isNetworkConnected()) {
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            return;
        }
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof Exception){
            //访问获得对应的Exception
            onError(ExceptionHandle.handleException(e));
        }else {
            //将Throwable 和 未知错误的status code返回
            onError(new ExceptionHandle.ResponeThrowable(e,ExceptionHandle.UNKNOWN));
        }
    }
    public abstract void onError(ExceptionHandle.ResponeThrowable responeThrowable);
    public abstract void onResponse(T t);

    @Override
    public void onCompleted() {
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }
    /**
     * @return boolean
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }
    public static void showToast(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}
