package org.com.asapplication.autodisposelifecycle;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.uber.autodispose.AutoDisposeConverter;

import org.com.asapplication.BMIViewActivity;
import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;
import org.com.asapplication.apputils.RxUtils;
import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.network.ApiLifecycleRequest;
import org.com.asapplication.rxjava.utils.Response;

import io.reactivex.annotations.NonNull;


public class RxLifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_lifecycle);
    }

    public void onClick(View view) {
        ApiLifecycleRequest.getInstance().getMessage(this,"fy", "auto", "auto", "hello%20world", new Response<Translation>() {
            @Override
            public void onError(String error) {
            }

            @Override
            public void onResponse(Translation translation) {
                AppLogger.e(translation.getContent().getOut());
            }
        });
//        ApiRequest.getMessage("fy", "auto", "auto", "hello%20world", new Response<Translation>() {
//            @Override
//            public void onError(String error) {
//            }
//
//            @Override
//            public void onResponse(Translation translation) {
//                AppLogger.e(translation.getContent().getOut());
//            }
//        });
    }
    private <T> AutoDisposeConverter<T> bindLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        return RxUtils.bindLifecycle(lifecycleOwner);
    }
    public void onClicka(View view) {
        startActivity(new Intent(this, BMIViewActivity.class));
        finish();
    }
}
