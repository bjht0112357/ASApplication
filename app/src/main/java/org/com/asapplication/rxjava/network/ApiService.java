package org.com.asapplication.rxjava.network;

import org.com.asapplication.rxjava.bean.GirlBean;
import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Class Des:
 * Created by bjh on 2018/3/6.
 */

public interface ApiService {
    //POST请求
    @FormUrlEncoded
    @POST("bjws/app.user/login")
    Observable<User> getUserInfo(@Field("tel") String tel, @Field("password") String pass);
    //GET请求
    @GET("ajax.php")
    Observable<Translation> getMessage(@Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);
    @GET("random/data/福利/1")
    Observable<GirlBean> getMeiZhi();
}
