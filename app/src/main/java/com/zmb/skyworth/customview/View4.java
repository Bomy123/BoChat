package com.zmb.skyworth.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.zmb.skyworth.bochat.R;
import com.zmb.skyworth.bochat.ScreenUtils;

/**
 * Created by zhangmingbao on 17-1-5.
 */

public class View4 extends View implements IView{
    private static int Hrect = 1250;
    private Bitmap bitmap = null;
    private Paint mPaint = null;
    Canvas mCanvas = null;
    public View4(Context context) {
        this(context,null);
    }

    public View4(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public View4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTools(getContext());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
//        canvas.drawRect(0,0,1000,700,mPaint);
        //canvas.drawBitmap(bitmap,30,530,mPaint);
        mCanvas.drawCircle(210,210,200,mPaint);
        changTools(getContext());
        mCanvas.drawRect(getLeft(),440,500,490,mPaint);

    }
    private void changTools(Context context)
    {
        //mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        mPaint.setShader(new LinearGradient(10,480,50,450,Color.BLUE,Color.RED, Shader.TileMode.REPEAT));
    }
private void initTools(Context context)
{
    if(mPaint == null) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_frame_background);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        mPaint.setColor(Color.GREEN);
        mPaint.setShader(new BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
    }
}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mPaint.setColor(Color.BLACK);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                mPaint.setColor(Color.GREEN);
                postInvalidate();
                break;
        }
        return true;
    }

    @Override
    public void Callback() {

    }

    @Override
    public void Release() {
        if(mCanvas != null)
            mCanvas = null;
if(bitmap != null&&!bitmap.isRecycled())
{
    bitmap.recycle();
    bitmap = null;
}
    }

}
