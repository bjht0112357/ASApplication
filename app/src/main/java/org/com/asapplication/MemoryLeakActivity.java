package org.com.asapplication;

import android.Manifest;
import android.arch.lifecycle.LifecycleOwner;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.uber.autodispose.AutoDisposeConverter;

import org.com.asapplication.apputils.AppLogger;
import org.com.asapplication.apputils.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class MemoryLeakActivity extends AppCompatActivity {
    private static NoMemoryLeakHandler noMemoryLeakHandler;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    //This Handler class should be static or leaks might occur (anonymous android.os.Handler)
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    //静态类继承Handler解决内存泄漏
    static class NoMemoryLeakHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String obj = (String) msg.obj;
            AppLogger.e(obj);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
        setTitle("内存泄漏测试");
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
        memoryleak();

    }
    public void onClick(View v){
        memoryleak();
//        noMemoryleak();
    }
    private void noMemoryleak() {
        Message message = Message.obtain();
        message.obj = "test memory leak";
        noMemoryLeakHandler = new NoMemoryLeakHandler();
        noMemoryLeakHandler.sendMessageDelayed(message, 2000);
    }

    private void memoryleak() {
        // ① 匿名线程持有 Activity 的引用，进行耗时操作
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(50000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        // ② 使用匿名 Handler 发送耗时消息
//        Message message = Message.obtain();
//        mHandler.sendMessageDelayed(message, 60000);
        // 每隔1s执行一次事件
        Observable.interval(1, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
//                            .as(bindLifecycle(this))
                            .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.i("接收数据", String.valueOf(aLong));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private <T> AutoDisposeConverter<T> bindLifecycle(@io.reactivex.annotations.NonNull LifecycleOwner lifecycleOwner) {
        return RxUtils.bindLifecycle(lifecycleOwner);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        noMemoryLeakHandler.removeCallbacksAndMessages(null);
    }
}
