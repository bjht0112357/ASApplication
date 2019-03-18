package org.com.asapplication.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;
import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.network.ApiRequest;
import org.com.asapplication.rxjava.utils.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxtestActivity extends AppCompatActivity {

    @BindView(R.id.tvOut)
    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtest);
        ButterKnife.bind(this);
        setTitle("RxtestActivity");
//        getNetworkData();
        ablObserver();
        ablConsumer();
    }

    private void ablObserver() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            //被观察者执行动作
            emitter.onNext("emitter1");
            emitter.onNext("emitter2");
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                //观察者监听动作，打印信息
                AppLogger.e(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    @SuppressLint("CheckResult")
    private void ablConsumer() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            //被观察者执行动作
            emitter.onNext("emitter1");
            emitter.onNext("emitter2");
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                AppLogger.e(s);
            }
        });
    }

    private void getNetworkData() {
        ApiRequest.getMessage("fy", "auto", "auto", "hello%20world", new Response<Translation>() {
            @Override
            public void onError(String error) {
            }

            @Override
            public void onResponse(Translation translation) {
                tvOut.setText(translation.getContent().getOut());
            }
        });
    }
}
