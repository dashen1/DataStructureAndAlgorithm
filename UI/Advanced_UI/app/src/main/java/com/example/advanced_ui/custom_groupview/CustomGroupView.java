package com.example.advanced_ui.custom_groupview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class CustomGroupView extends ViewGroup {

    private static final int OFFSET = 100;

    public CustomGroupView(Context context) {
        super(context);
    }

    public CustomGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //MeasureSpec = specMode + specSize
        //1、测量自身
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 2、为每个子view计算测量的限制信息
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 3、把上一步确定的限制信息，传递给子view,然后子view开始measure
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            // layoutParams.width 就是xml文件里面定义的layout_width
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec,0,layoutParams.width);
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec,0,layoutParams.height);
            child.measure(childWidthSpec, childHeightSpec);
        }

        int width = 0;
        int height = 0;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    int widthAddOffset = i * OFFSET + child.getMeasuredWidth();
                    width = Math.max(width, widthAddOffset);
                }
                break;
            default:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                     height += child.getMeasuredHeight();
                }
                break;
            default:
                break;
        }

        setMeasuredDimension(width, height);
    }


    // 各自具体的ViewGroup才会使用onLayout,子view不需要
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 1.遍历子view
        // 2.确定自己的规则
        // 3.子view的测量尺寸
        // 4.left,top,right,bottom
        // 5.child.layout

        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            left = i * OFFSET;
            right = left + child.getMeasuredWidth();
            bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
            top += child.getMeasuredHeight();
        }
    }
}
