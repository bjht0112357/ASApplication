package org.com.asapplication.rxBus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.com.asapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RxBusSendActivity extends AppCompatActivity {

    @BindView(R.id.tvGetMessage)
    TextView tvGetMessage;
    @BindView(R.id.btnGo)
    TextView btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus_send);
        ButterKnife.bind(this);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBusMessage rxBusMessage = new RxBusMessage(tvGetMessage.getText().toString(),null);
                RxBus.getInstance().post(rxBusMessage);
                finish();
            }
        });


    }


}
