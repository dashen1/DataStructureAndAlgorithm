package com.example.advanced_ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasTransformation extends View {

    private Paint mPaint;
    public CanvasTransformation(Context context) {
        super(context);
        init();
    }

    public CanvasTransformation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mPaint.setColor(Color.YELLOW);

        //canvas.translate(100, 100);
        canvas.drawColor(Color.RED);
        canvas.save();

        canvas.clipRect(new Rect(100, 100, 800, 800));
        canvas.drawColor(Color.GREEN);

//        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);
//
//        canvas.translate(0, 300);
//        canvas.drawRect(new Rect(0, 0, 200, 200), mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(15);



    }
}
