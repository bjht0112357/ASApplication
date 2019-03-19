package org.com.asapplication.rxBus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import org.com.asapplication.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxBusBindingTestActivity extends AppCompatActivity {

    @BindView(R.id.tvGetMessage)
    TextView tvGetMessage;
    @BindView(R.id.btnGo)
    TextView btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus_test);
        ButterKnife.bind(this);
        //https://blog.csdn.net/weixin_41307234/article/details/78962976
        Disposable register = RxBus.getInstance().register(RxBusMessage.class, new Consumer<RxBusMessage>() {
            @Override
            public void accept(RxBusMessage rxBusMessage) throws Exception {
                tvGetMessage.setText(rxBusMessage.getType());
            }
        });
        //rxbinding
        RxView.clicks(btnGo)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Intent i =new Intent(RxBusBindingTestActivity.this,RxBusSendActivity.class);
                        startActivity(i);
                    }
                })
                .subscribe();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
