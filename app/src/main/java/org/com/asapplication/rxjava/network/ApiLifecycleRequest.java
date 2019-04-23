package org.com.asapplication.rxjava.network;

import android.arch.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDisposeConverter;

import org.com.asapplication.apputils.RxUtils;
import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.utils.Response;
import org.com.asapplication.rxjava.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DeMon on 2017/9/6.
 */

public class ApiLifecycleRequest {

    /**
     * 获取Retrofit
     * @return ApiService
     */
    public  ApiService getAppService(){
        return RetrofitUtils.getInstance().setCreate(ApiService.class);
    }
    private ApiLifecycleRequest() {
    }

    private static volatile ApiLifecycleRequest instance;

    public static ApiLifecycleRequest getInstance() {
        if (instance == null) {
            synchronized (ApiLifecycleRequest.class) {
                if (instance == null) {
                    instance = new ApiLifecycleRequest();
                }
            }
        }
        return instance;
    }
//    /**
//     * 封装线程管理和订阅的过程
//     */
//    public  void ApiSubscribe(@NonNull LifecycleOwner lifecycleOwner,Observable<T> observable, Observer observer) {
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
    public void getMessage(@NonNull LifecycleOwner lifecycleOwner, String a, String f, String t, String w, Response<Translation> observer){
//        ApiSubscribe(lifecycleOwner,getAppService().getMessage(a, f, t, w),observer);
        getAppService().getMessage(a, f, t, w)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle(lifecycleOwner)).subscribe(observer);
    }
    private <T> AutoDisposeConverter<T> bindLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        return RxUtils.bindLifecycle(lifecycleOwner);
    }

}
