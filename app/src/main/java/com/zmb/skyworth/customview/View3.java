package com.zmb.skyworth.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.zmb.skyworth.bochat.R;

/**
 * Created by zhangmingbao on 17-1-4.
 */

public class View3 extends View implements IView{
    Paint mPaint = null;
    Paint.FontMetrics fontMetrics = null;
String TEXT = "ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    public View3(Context context) {
        this(context, null);
    }

    public View3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTools();
    }

    private void initTools() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(100);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setColor(Color.BLUE);
        fontMetrics = mPaint.getFontMetrics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(TEXT,0, Math.abs(fontMetrics.top),mPaint);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(0,Math.abs(fontMetrics.top)/2,canvas.getWidth(),Math.abs(fontMetrics.top)/2,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0,Math.abs(fontMetrics.top),TEXT.length()*100,Math.abs(fontMetrics.top),mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0,Math.abs(fontMetrics.ascent),TEXT.length()*100,Math.abs(fontMetrics.ascent),mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawLine(0,Math.abs(fontMetrics.bottom),TEXT.length()*100,Math.abs(fontMetrics.bottom),mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0,Math.abs(fontMetrics.descent),TEXT.length()*1000,Math.abs(fontMetrics.descent),mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0,Math.abs(fontMetrics.leading),TEXT.length()*100,Math.abs(fontMetrics.leading),mPaint);

    }

    @Override
    public void Callback() {

    }

    @Override
    public void Release() {

    }
//private int r = 10;
//    private int x = 500;
//    private int y = 500;
//    private Bitmap bitmap = null;
//    private Paint simPaint = null;
//    float preX = 0;
//    float preY = 0;
//    Path mpath =null;
//    public View3(Context context) {
//        this(context,null);
//    }
//
//    public View3(Context context, AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public View3(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initPaint();
//        initRes(getContext());
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /**
//                 * ColorMatrixColorFilter
//                 */
////                ColorMatrix colorMatrix = new ColorMatrix(new float[]
////                        {
////                                0.5F, 0, 1, 0, 0,
////                                1,0.5F, 0, 0, 0,
////                                0, 0,1.5F, 0, 0,
////                                0, 0, 0, 1, 0,
////
////                        });
////                simPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//                /**
//                 * LightingColorFilter
//                 */
//                simPaint.setColorFilter(new LightingColorFilter(0XFFFFFFFF,0X00FFFF00));
//                /**
//                 *
//                 */
//                //simPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
//                /**
//                 *
//                 */
//                simPaint.setARGB(123,123,123,123);
//                postInvalidate();
//            }
//        });
//
//    }
//    private void initRes(Context context)
//    {
//        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_frame_background);
//    }
//    private void initPaint()
//    {
//        mpath = new Path();
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]
//                {
//                        0.5F, 0, 0, 0, 0,
//                        0,0.5F, 0, 0, 0,
//                        0, 0,0.5F, 0, 0,
//                        0, 0, 0, 1, 0,
//
//                });
//        simPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        simPaint.setStyle(Paint.Style.STROKE);
//        simPaint.setColor(Color.BLACK);
//        simPaint.setStrokeWidth(20);
//        //simPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//    }
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawBitmap(bitmap,20,20,simPaint);
//        canvas.drawCircle(x,y,r,simPaint);
////        canvas.drawRect(400,0,900,500,simPaint);
////        canvas.drawRoundRect(0,500,500,1000,20,20,simPaint);
////        canvas.drawOval(500,500,1000,1000,simPaint);
//        canvas.drawPath(mpath,simPaint);
//
//    }
//    public synchronized void setR(int r)
//    {
//        this.r = r;
//        postInvalidate();
//    }
//    public synchronized void setX(int x)
//    {
//        this.x = x;
//        postInvalidate();
//    }
//    public synchronized void setY(int y)
//    {
//        this.y = y;
//        postInvalidate();
//    }
//    private void cal(float org,float end,double num)
//    {
//        preX = 0;
//        preY=500;
//        mpath.moveTo(preX,preY);
//        float x = org;
//        for(;x <= end;x += num)
//        {
//            float y = Float.parseFloat(String.valueOf(Math.sin(x)));
//            System.out.println("("+x+","+y+")");
//
//            mpath.quadTo(preX,preY,x,y*10+500);
//            preX = x;
//            preY = y*100+500;
//            postInvalidate();
//        }
//    }
//    @Override
//    public void Callback() {
//        cal(0,1000,50);
//    }
}
