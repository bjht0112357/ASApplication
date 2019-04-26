package org.com.asapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.com.asapplication.animation.FrameAnimationActivity;
import org.com.asapplication.myservice.TestServiceActivity;
import org.com.asapplication.tim.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tvMemoryLeak)
    TextView tvMemoryLeak;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.tvFrameAnimation)
    TextView tvFrameAnimation;
    @BindView(R.id.tvLoginTim)
    TextView tvLoginTim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvMemoryLeak.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MemoryLeakActivity.class)));
        tvService.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestServiceActivity.class)));
        tvFrameAnimation.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FrameAnimationActivity.class)));
        tvLoginTim.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
//        tvLoginTim.setOnClickListener(v -> AppUtils.copy(getApplication(),"测试复制"));
    }
}
