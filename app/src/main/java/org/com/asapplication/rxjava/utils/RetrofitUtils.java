package org.com.asapplication.rxjava.utils;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public class RetrofitUtils {
    private Retrofit mRetrofit;
    private String baseUrl;
    private static RetrofitUtils mRetrofitManager;
    private RetrofitUtils(String baseUrl){
        this.baseUrl=baseUrl;
        initRetrofit();
    }
    public static synchronized RetrofitUtils getInstance(String baseUrl){
        if (mRetrofitManager == null){
            mRetrofitManager = new RetrofitUtils(baseUrl);
        }
        return mRetrofitManager;
    }
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttp3Utils.getOkHttpClient())
                .build();
    }
    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }
}
