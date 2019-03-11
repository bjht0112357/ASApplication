package org.com.asapplication.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.com.asapplication.R;
import org.com.asapplication.picasso.transformation.BlurTransformation;
import org.com.asapplication.picasso.transformation.GrayTransformation;
import org.com.asapplication.picasso.transformation.RoundTransform;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicassoActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);
//        旧版
//        Picasso.with(this)
//                .load("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
//                .into(imageView);
        //新版
        Picasso.get()
                .load("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
                .resize(400,400)
                .centerCrop()
                .transform(new GrayTransformation())//度灰处理
                .into(imageView);
        Picasso.get()
                .load("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
                .resize(400,400)
                .centerCrop()
                .transform(new BlurTransformation(this))//高斯模糊
                .into(imageView2);
        Picasso.get()
                .load("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
                .resize(400,400)
                //图片的scaleType=centerCrop时没有圆角的效果
                //.centerCrop()
                .transform(new RoundTransform(20))
                .into(imageView3);

    }
}
