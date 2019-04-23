package org.com.asapplication.animation;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.com.asapplication.R;
import org.com.asapplication.animation.widget.FrameAnimation;

/**
 * 有内存溢出的问题
 *Android帧动画实现,防OOM,比原生动画集节约超过十倍的资源
 *https://blog.csdn.net/u013651026/article/details/81129472
 */
public class FrameAnimationActivity extends AppCompatActivity {

    private static final String TAG = "ansen";
    ImageView image;
    FrameAnimation frameAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
       image = findViewById(R.id.imageView);

        // 每50ms一帧 循环播放动画
        frameAnimation = new FrameAnimation(image, getRes(), 50, true);
        frameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                Log.d(TAG, "start");
            }

            @Override
            public void onAnimationEnd() {
                Log.d(TAG, "end");
            }

            @Override
            public void onAnimationRepeat() {
                Log.d(TAG, "repeat");
            }
        });

        image.setOnClickListener(v -> {
            // 实现点击 暂停和继续播放动画
            if (frameAnimation.isPause()) {
                Log.d(TAG, "restart");
                frameAnimation.restartAnimation();
            } else {
                Log.d(TAG, "pause");
                frameAnimation.pauseAnimation();
            }
        });

//        循环播放动画,循环间隔为4000ms
//        FrameAnimation frameAnimation = new FrameAnimation(image, getRes(), 50, 4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        frameAnimation.release();
        image.setOnClickListener(null);
    }

    /**
     * 获取需要播放的动画资源
     */
    private int[] getRes() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.c);
        int len = typedArray.length();
        int[] resId = new int[len];
        for (int i = 0; i < len; i++) {
            resId[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return resId;
    }
}
