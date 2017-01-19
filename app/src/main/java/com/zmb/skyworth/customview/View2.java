package com.zmb.skyworth.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zmb.skyworth.bochat.R;
import com.zmb.skyworth.bochat.ScreenUtils;

/**
 * Created by zhangmingbao on 17-1-4.
 */

public class View2 extends View {
    float preX,preY;
    Paint mPaint = null;
    Canvas mCanvas = null;
    Bitmap bgBitmap = null;
    Bitmap fgBitmap = null;
    Path mPath = null;
    int screenH, screenW;
    Context mContext = this.getContext();
float MIN_MOVE_LENGTH = 1;
    public View2(Context context) {
        this(context, null);
    }

    public View2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getScreenSize();
        initRes();
        initTools();

    }

    private void initTools() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStrokeWidth(10);
        mPaint.setARGB(128, 255, 0, 0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mCanvas = new Canvas(fgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    private void getScreenSize() {
        screenH = ScreenUtils.getScreenHeight(getContext());
        screenW = ScreenUtils.getScreenWidth(getContext());
    }

    private void initRes() {
        fgBitmap = Bitmap.createBitmap(screenH,screenW, Bitmap.Config.ARGB_8888);
        bgBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.img_frame_background);
        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, screenW, screenH, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBitmap,0,0,null);
        canvas.drawBitmap(fgBitmap,0,0,null);
        mCanvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        System.out.println("x ="+ x);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
           float d = Math.max(Math.abs(x - preX),Math.abs(y - preY));
                System.out.println("d ="+ d);
                if(d >= MIN_MOVE_LENGTH)
                {
                    System.out.println("mPath.quadTo("+preX+","+preX+","+(x + preX)/2+","+(y+preY)/2);
                    mPath.quadTo(preX,preY,(x + preX)/2,(y+preY)/2);
                    preX = x;
                    preY = y;
                }

                break;
        }
        invalidate();
        return true;

    }
}
