package org.com.asapplication.heartview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.com.asapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeartAnimActivity extends AppCompatActivity {
    @BindView(R.id.heart_layout)
    HeartLayout mHeartLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_anim);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        // 添加飘星动画
        mHeartLayout.addFavor();
    }
}
