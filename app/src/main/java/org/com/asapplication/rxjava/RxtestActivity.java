package org.com.asapplication.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.com.asapplication.R;
import org.com.asapplication.rxjava.bean.Translation;
import org.com.asapplication.rxjava.network.ApiService;
import org.com.asapplication.rxjava.utils.ExceptionHandle;
import org.com.asapplication.rxjava.utils.ResponseSubscriber;
import org.com.asapplication.rxjava.utils.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxtestActivity extends AppCompatActivity {

    @BindView(R.id.tvOut)
    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtest);
        ButterKnife.bind(this);
        setTitle("RxtestActivity");
        ApiService apiService = RetrofitUtils.getInstance("http://fy.iciba.com/").setCreate(ApiService.class);
        //"apiService.getCall("fy", "auto", "auto", "hello%20world")." 输入ssb自动索引
        apiService.getCall("fy", "auto", "auto", "hello%20world").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<Translation>() {
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable responeThrowable) {

                    }

                    @Override
                    public void onResponse(Translation result) {
                        tvOut.setText(result.toString());
                    }
                });
    }
}
