package org.com.asapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Class Des:
 * Created by bjh on 2018/3/12.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                Log.e("MeasureSpec", "MeasureSpec.UNSPECIFIED");
                mySize = defaultSize;
                break;
            }


            /**
            * 最大模式，对应的就是wrap_content
            */
            //public static final int AT_MOST     = 2 << MODE_SHIFT;
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                Log.e("MeasureSpec", "MeasureSpec.AT_MOST");
                mySize = size;
                break;
            }
            /**
             * 精确模式，对应的是match_parent和具体值，比如100dp
             * */
            //public static final int EXACTLY     = 1 << MODE_SHIFT;
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                Log.e("MeasureSpec", "MeasureSpec.EXACTLY");
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = (int) ((getX() - getLeft()) + r);
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = (int) ((getY() - getTop()) + r);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //开始绘制
        canvas.drawCircle(centerX, centerY, r, paint);


    }

}
