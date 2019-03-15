package org.com.asapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.imageView)
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageView.setOnClickListener(v -> {

        });
//        vc.isEqualsIgnoreCase()
        // 1.1使用匿名内部类
        new Thread(() -> System.out.println("Hello world !")).start();
    }
}
