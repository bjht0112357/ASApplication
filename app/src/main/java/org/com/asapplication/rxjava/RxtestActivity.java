package org.com.asapplication.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.com.asapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RxtestActivity extends AppCompatActivity {

    @BindView(R.id.tvOut)
    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtest);
        ButterKnife.bind(this);
        setTitle("RxtestActivity");
//        ApiRequest.getMessage("fy", "auto", "auto", "hello%20world", new Response<Translation>() {
//            @Override
//            public void onError(String error) {
//
//            }
//
//            @Override
//            public void onResponse(Translation translation) {
//                tvOut.setText(translation.getContent().getOut());
//            }
//        });
    }
}
