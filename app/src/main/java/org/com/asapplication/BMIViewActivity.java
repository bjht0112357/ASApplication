package org.com.asapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.com.asapplication.widget.BMIView;

import butterknife.BindView;

public class BMIViewActivity extends AppCompatActivity {


    @BindView(R.id.imageView)
    ImageView imageView;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiview);
        BMIView view1 = findViewById(R.id.BMIView1);
        BMIView view2 = findViewById(R.id.BMIView2);
        view2.setLeftColor(getResources().getColor(R.color.gray));
        BMIView view3 = findViewById(R.id.BMIView3);
        // 代码设置各种值
        view3.setTitle("代码设置的标题");
    }
}
