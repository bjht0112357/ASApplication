package org.com.asapplication.rxjava.network;

import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.utils.Response;
import org.com.asapplication.rxjava.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DeMon on 2017/9/6.
 */

public class ApiRequest {

    /**
     * 获取Retrofit
     * @return ApiService
     */
    public static ApiService getAppService(){
        return RetrofitUtils.getInstance().setCreate(ApiService.class);
    }
    /**
     * 封装线程管理和订阅的过程
     */
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    public static  void getMessage(String a,  String f, String t,  String w,Response<Translation> observer){
        ApiSubscribe(getAppService().getMessage(a, f, t, w),observer);
    }


}
