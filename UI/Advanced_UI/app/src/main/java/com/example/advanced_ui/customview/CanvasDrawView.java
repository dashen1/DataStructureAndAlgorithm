package com.example.advanced_ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasDrawView extends View {

    private float mDensity;
    private Paint mPaint;

    public CanvasDrawView(Context context) {
        super(context);
        initView(context);
    }

    public CanvasDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        DisplayMetrics displayMetrics;
        displayMetrics = context.getResources().getDisplayMetrics();
        mDensity = displayMetrics.density;
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        //smooths out the edges of what is being drawn
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        //set the paint's color
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(15*mDensity);

        //draw a circle
        canvas.drawCircle(40*mDensity, 40*mDensity, 30*mDensity, mPaint);

    }
}
