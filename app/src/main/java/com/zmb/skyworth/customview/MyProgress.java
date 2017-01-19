package com.zmb.skyworth.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangmingbao on 17-1-13.
 */

public class MyProgress extends View implements IView{
    private int[] color = {Color.BLUE,Color.RED};
    private float[] color_m = null;
    private float left = 0;
    private float right = 30;
    private float top = 1000;
    private float bottom = top + 50;
    private float position_x1 = 0;
    private float position_y1 = top;
    private float position_x2 = 50;
    private float position_y2 = top + 25;
    private float height = 10;
    private float width =  25;
    private float width_tmp = 25;
    private Paint mPaint = null;
    public MyProgress(Context context) {
        this(context,null);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTools();
    }
    public MyProgress setColor(int[] color)
    {
        this.color = color;
        return this;
    }
    public MyProgress setColor_m(float[] color_m)
    {
        this.color_m = color_m;
        return this;
    }
    public void do1()
    {

    }
    public MyProgress setSize(int progress_length,int progress_height)
    {
        this.width = progress_length;
        this.width_tmp = progress_length;
        this.height = progress_height;
        return this;
    }
    public MyProgress setRectPosition(int x,int y)
    {
        this.left = x;
        this.top = y;
        this.right = x + width;
        this.bottom = y + height;
        return this;
    }
    public MyProgress setColorPosition(float position_x1,float position_y1,float position_x2,float position_y2)
    {
        this.position_x1 = position_x1;
        this.position_x2 = position_x2;
        this.position_y1 = position_y1;
        this.position_y2 = position_y2;
        return this;
    }

    public MyProgress init(int[] color,float left,float top,float right, float bottom,float position_x1,float position_y1,float position_x2,float position_y2)
    {
        this.color = color;
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.position_x1 = position_x1;
        this.position_x2 = position_x2;
        this.position_y1 = position_y1;
        this.position_y2 = position_y2;
        this.width = right - left;
        this.height = bottom - top;
        return this;
    }
     public MyProgress setRect(float left,float top,float right, float bottom)
     {
         this.left = left;
         this.top = top;
         this.bottom = bottom;
         this.right = right;
         this.width = right - left;
         this.height = bottom - top;
         return this;
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(left,top,right,bottom,mPaint);

    }
    private void initTools()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(new LinearGradient(position_x1,position_y1,position_x2,position_y2,color,color_m, Shader.TileMode.REPEAT));
    }

    @Override
    public void Callback() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(width = 0;width <= width_tmp;width += 3)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    right = left + width;
                    postInvalidate();
                }
                if(width < width_tmp)
                {
                    width = width_tmp;
                    postInvalidate();
                }
            }
        }).start();
    }

    @Override
    public void Release() {
        mPaint = null;
    }
}
