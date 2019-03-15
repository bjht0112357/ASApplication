package org.com.asapplication.rxjava.utils;


import org.com.asapplication.rxjava.constants.AppConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public class RetrofitUtils {
    private Retrofit mRetrofit;
    private static RetrofitUtils mRetrofitUtils;
    private RetrofitUtils(){
        initRetrofit();
    }
    public static synchronized RetrofitUtils getInstance(){
        if (mRetrofitUtils == null){
            mRetrofitUtils = new RetrofitUtils();
        }
        return mRetrofitUtils;
    }
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttp3Utils.getOkHttpClient())
                .build();
    }
    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }
}
