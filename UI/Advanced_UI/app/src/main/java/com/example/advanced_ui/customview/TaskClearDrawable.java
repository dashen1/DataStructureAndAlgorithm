package com.example.advanced_ui.customview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.AnimationUtils;

import com.example.advanced_ui.R;

public class TaskClearDrawable extends Drawable{
    private static final String TAG = "Zero";
    //anmator state
    private final int STATE_ORIGIN = 0;//初始状态
    private final int STATE_ROTATE = STATE_ORIGIN + 1;//外圈旋转
    private final int STATE_UP = STATE_ROTATE + 1;//上移
    private final int STATE_DOWN = STATE_UP + 1;//下移
    private final int STATE_FINISH = STATE_DOWN + 1;//结束

    String getState(final int state) {
        String result = "STATE_ORIGIN";
        switch (state) {
            case STATE_ORIGIN:
                result = "STATE_ORIGIN";
                break;
            case STATE_ROTATE:
                result = "STATE_ROTATE";
                break;
            case STATE_UP:
                result = "STATE_UP";
                break;
            case STATE_DOWN:
                result = "STATE_DOWN";
                break;
            case STATE_FINISH:
                result = "STATE_FINISH";
                break;
            default:
                break;
        }
        return result;
    }

    //animator duration time
    private final long DURATION_ROTATE = 1250;//外圈旋转时长
    private final long DURATION_CLEANNING = 250;//× 缩小至 0的时长
    private final long DURATION_POINT_UP = 250;// 点 往上移动的时长
    private final long DURATION_POINT_DOWN = 350;// 点 往下移动的时长
    private final long DURATION_FINISH = 200;// 短边缩放的时长
    private final long DURATION_CLEANNING_DELAY = 1000;// cleanning 时长
    private final long DURATION_ORIGIN_DELAY = 3000;// 返回初始状态的时长

    private final float PI_DEGREE = (float) (180.0f / Math.PI);//180度/π是1弧度对应多少度,这里表示一弧度的大小(57.30)
    private final float DRAWABLE_WIDTH = 180.0f;//drawable_width 宽度
    private final float ROTATE_DEGREE_TOTAL = -1080.0f;//总共旋转的角度 即旋转3圈 6π

    private final float PAINT_WIDTH = 4.0f;//画×的笔的宽度
    private final float PAINT_WIDTH_OTHER = 1.0f;//画其他的笔的宽度
    private final float CROSS_LENGTH = 62.0f;//×的长度
    private final float CORSS_DEGREE = 45.0f / PI_DEGREE;//π/4 三角函数计算用 sin(π/4) = cos(π/4) = 0.707105
    private final float UP_DISTANCE = 24.0f;//往上移动的距离
    private final float DOWN_DISTANCE = 20.0f;//往下移动的距离
    private final float FORK_LEFT_LEN = 33.0f;//左短边长度
    private final float FORK_LEFT_DEGREE = 40.0f / PI_DEGREE;//左短边弧度
    private final float FORK_RIGHT_LEN = 50.0f;//右长边长度
    private final float FORK_RIGHT_DEGREE = 50.0f / PI_DEGREE;//右长边弧度
    private final float CIRCLE_RADIUS = 3.0f;//圆点半径


    private int mWidth, mHeight;
    private int mAnimState = STATE_ORIGIN;//状态
    private float mCleanningScale, mRotateDegreeScale;    //cleanning 缩放，旋转缩放
    private float mScale = 0.0f;
    private float mPaintWidth;//画笔宽度
    private float mPaintWidthOther;
    private float mViewScale;
    private float mCenterX, mCenterY;
    private float mCrossLen,oldCrossLen;
    private float mPointRadius;
    private float mForkLeftLen, mForkRightLen;
    private float mPointUpLen, mPointDownLen;

    private Paint mPaint;
    private Paint mLinePaint;
    private Bitmap mBgBitmap;
    private Bitmap mCircleBitmap;
    private TimeInterpolator fast_out_slow_in;
    private TimeInterpolator fast_out_linear_in;
    private AnimatorSet mAnimatorSet;
    private Matrix mRotateMatrix = new Matrix();

    public TaskClearDrawable(Context context, int width, int height) {
        super();
        init(context, width, height);
    }

    private void init(Context context, int width, int height) {
        mWidth = width;
        mHeight = height;
        mPaint = new Paint();
        mLinePaint = new Paint();

        Bitmap tempCircleBitmap = BitmapFactory.
                decodeResource(context.getResources(), R.drawable.circle);
        Bitmap tempBgBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);

        mCircleBitmap =
                Bitmap.createScaledBitmap(tempCircleBitmap, mWidth, mHeight, true);
        mBgBitmap =
                Bitmap.createScaledBitmap(tempBgBitmap, mWidth, mHeight, true);
        mViewScale = mWidth / DRAWABLE_WIDTH;
        Log.i(TAG, "init: mViewScale= " + mViewScale);

        if (mCircleBitmap != tempCircleBitmap) {
            tempCircleBitmap.recycle();
        }

        if (mBgBitmap != tempBgBitmap) {
            tempBgBitmap.recycle();
        }

        mCenterX = mWidth / 2.0f;
        mCenterY = mHeight / 2.0f;
        mPaintWidth = PAINT_WIDTH * mViewScale;
        mPaintWidthOther = PAINT_WIDTH_OTHER * mViewScale;
        mCrossLen = CROSS_LENGTH * mViewScale;
        mPointRadius = CIRCLE_RADIUS * mViewScale;
        mForkLeftLen = FORK_LEFT_LEN * mViewScale;
        mForkRightLen = FORK_RIGHT_LEN * mViewScale;
        mPointUpLen = UP_DISTANCE * mViewScale;
        mPointDownLen = DOWN_DISTANCE * mViewScale;

        mCleanningScale = 1.0f;
        mRotateDegreeScale = 0.0f;

        fast_out_slow_in = AnimationUtils.loadInterpolator(
                context, android.R.interpolator.fast_out_slow_in);
        fast_out_linear_in = AnimationUtils.loadInterpolator(
                context, android.R.interpolator.fast_out_linear_in);
    }

    @Override
    public void draw(Canvas canvas) {

        float x1,y1,x2,y2,x3,y3,x4,y4;
        float length;//叉的长度
        float sin_45 = (float) Math.sin(CORSS_DEGREE);
        float cos_40 = (float) Math.cos(FORK_LEFT_DEGREE);
        float sin_40 = (float) Math.sin(FORK_LEFT_DEGREE);

        float cos_50 = (float) Math.cos(FORK_RIGHT_DEGREE);
        float sin_50 = (float) Math.sin(FORK_RIGHT_DEGREE);

        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffffff);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPaintWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 绘制背景
        canvas.drawBitmap(mBgBitmap, 0, 0, mPaint);

        // 画辅助线
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(4);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setPathEffect(new DashPathEffect(new float[]{20,20}, 0));
        canvas.drawLine(0, mCenterX, mWidth, mCenterY, mLinePaint);
        canvas.drawLine(mCenterX, 0, mCenterX, mHeight, mLinePaint);


      // 根据五种不同的状态来绘制
        switch (mAnimState) {
            case STATE_ORIGIN:// 绘制mCircleBitmap 绘制×
                length = mCrossLen * sin_45 / 2.0f;
                x1 = mCenterX - length;
                y1 = mCenterY - length;
                x2 = mCenterX + length;
                y2 = mCenterY + length;

                x3 = mCenterX + length;
                y3 = mCenterY - length;
                x4 = mCenterX - length;
                y4 = mCenterY + length;
                drawPath(canvas, mPaint, x1,y1,x2,y2,x3,y3,x4,y4);
                break;
            case STATE_ROTATE:// 旋转 matrix mCircleBitmap
                break;
            case STATE_UP:// 绘制圆点
                break;
            case STATE_FINISH:// 画勾勾
                break;
        }

    }


    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
        invalidateSelf();
    }

    private void drawPath(Canvas canvas, Paint paint,
                          float x1, float y1, float x2, float y2,
                          float x3, float y3, float x4, float y4) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.moveTo(x3, y3);
        path.lineTo(x4, y4);

        canvas.drawPath(path, paint);
    }



    public boolean isRunning() {
        if (null != mAnimatorSet) {
            return mAnimatorSet.isRunning();
        } else {
            return false;
        }
    }
}
