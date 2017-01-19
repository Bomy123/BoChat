package com.zmb.skyworth.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.icu.util.Measure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.zmb.skyworth.bochat.R;

/**
 * Created by zhangmingbao on 17-1-3.
 */

public class View1 extends View implements IView {
    private int r = 10;
    private int x = 500;
    private int y = 500;
    private Bitmap bitmap = null;
    private Paint simPaint = null;
    private Object obj = new Object();
    float preX = 0;
    float preY = 0;
    private boolean isRelease = false;
    Path mpath = null;
    private Canvas mCanvas = null;

    public View1(Context context) {
        this(context, null);
    }

    public View1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isRelease = false;
        initPaint();
        initRes(getContext());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * ColorMatrixColorFilter
                 */
//                ColorMatrix colorMatrix = new ColorMatrix(new float[]
//                        {
//                                0.5F, 0, 1, 0, 0,
//                                1,0.5F, 0, 0, 0,
//                                0, 0,1.5F, 0, 0,
//                                0, 0, 0, 1, 0,
//
//                        });
//                simPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                /**
                 * LightingColorFilter
                 */
                simPaint.setColorFilter(new LightingColorFilter(0XFFFFFFFF, 0X00FFFF00));
                /**
                 *
                 */
                //simPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
                /**
                 *
                 */
                simPaint.setARGB(123, 123, 123, 123);
                postInvalidate();
            }
        });

    }

    private void initRes(Context context) {
        if (bitmap == null)
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_frame_background);
    }

    private void initPaint() {
        if (simPaint == null) {
            mpath = new Path();
            ColorMatrix colorMatrix = new ColorMatrix(new float[]
                    {
                            0.5F, 0, 0, 0, 0,
                            0, 0.5F, 0, 0, 0,
                            0, 0, 0.5F, 0, 0,
                            0, 0, 0, 1, 0,

                    });
            simPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            simPaint.setStyle(Paint.Style.STROKE);
            simPaint.setColor(Color.BLACK);
            simPaint.setStrokeWidth(20);
            //simPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

                if (mCanvas == null) {
                    mCanvas = canvas;
                }
                mCanvas.drawBitmap(bitmap, 0, 0, simPaint);
                mCanvas.drawCircle(x, y, r, simPaint);
//        canvas.drawRect(400,0,900,500,simPaint);
//        canvas.drawRoundRect(0,500,500,1000,20,20,simPaint);
//        canvas.drawOval(500,500,1000,1000,simPaint);
                mCanvas.drawPath(mpath, simPaint);
            }
    public synchronized void setR(int r) {
        this.r = r;
        postInvalidate();
    }

    public synchronized void setX(int x) {
        this.x = x;
        postInvalidate();
    }

    public synchronized void setY(int y) {
        this.y = y;
        postInvalidate();
    }

    private void cal(float org, float end, double num) {
        preX = 0;
        preY = 500;
        mpath.moveTo(preX, preY);
        float x = org;
        for (; x <= end; x += num) {
            float y = Float.parseFloat(String.valueOf(Math.sin(x)));
            System.out.println("(" + x + "," + y + ")");

            mpath.quadTo(preX, preY, x, y * 10 + 500);
            preX = x;
            preY = y * 100 + 500;
            postInvalidate();
        }
    }

    @Override
    public void Callback() {
        cal(0, 1000, 50);
    }

    @Override
    public void Release() {
        synchronized (obj) {
            if (mCanvas != null) {
                mCanvas = null;
            }
            if (simPaint != null) {
                simPaint = null;
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
            isRelease = true;
        }
    }
}
