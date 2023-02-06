package com.example.advanced_ui.custom_groupview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.advanced_ui.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";
    private List<View> lineViews;// 单行view
    private List<List<View>> views;// 多行view
    private List<Integer> heights;

    private int gravity;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, 0);
    }


    public void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Layout);
        gravity = a.getInt(R.styleable.FlowLayout_android_gravity, -1);
        a.recycle();
    }

    public void init() {
        lineViews = new ArrayList<>();
        views = new ArrayList<>();
        heights = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量自身大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 记录当前行的宽度和高度
        int lineWidth = 0;// 当前行子view的宽度之和
        int lineHeight = 0;// 当前行所有子view中高度的最大值

        // 整个流式布局的宽度和高度
        int flowlayoutWidth = 0;// 所有行中宽度的最大值
        int flowlayoutHeight = 0;// 所有行高度的累加

        // 初始化参数列表 onMeasure有可能调用多次 所以初始化也要多次重新初始化
        init();

        // 遍历子view，对子view进行测量，分配到具体的行
        int childCount = this.getChildCount();
        ViewGroup.LayoutParams lp;
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            lp = child.getLayoutParams();
            // 查看当前行的剩余宽度是否可以容纳下一个子view
            // 如果放不下，就换行 然后保存当前行的所有子view，累加行高，当前的宽度、高度置零
            if (lineWidth + childWidth > widthSize) {
                views.add(lineViews);
                lineViews = new ArrayList<>();
                flowlayoutWidth = Math.max(flowlayoutWidth, lineWidth);
                flowlayoutHeight += lineHeight;
                heights.add(lineHeight);
                lineWidth = 0;
                lineHeight = 0;
            }
            lineViews.add(child);
            lineWidth += childWidth;
            // 高度不为MATCH_PARENT的时候先不处理
            if (lp.height != LayoutParams.MATCH_PARENT) {

                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i == childCount - 1) {// 最后一个子view
                flowlayoutHeight += lineHeight;
                flowlayoutWidth = Math.max(flowlayoutWidth, lineWidth);
                heights.add(lineHeight);
                views.add(lineViews);
            }
        }
        remeasureChild(widthMeasureSpec, heightMeasureSpec);
        // FlowLayout最终宽高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY?widthSize: flowlayoutWidth, widthMode == MeasureSpec.EXACTLY?widthSize: flowlayoutHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int lineCount = views.size();

        int currentX = 0;
        int currentY = 0;
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = this.views.get(i);
            int lineHeight = heights.get(i);
            // 遍历当前行的子view
            int size = lineViews.size();
            for (int j = 0; j < size; j++) {
                View child = lineViews.get(j);
                int left = 0;
                int top = 0;
                int right = left + child.getMeasuredWidth();
                int bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                // 当前行下一个子view的 left
                currentX += child.getMeasuredWidth();
            }
            currentY += lineHeight;
            currentX = 0;
        }
    }

    private void remeasureChild(int widthMeasureSpec, int heightMeasureSpec) {
        int lineSize = views.size();
        for (int i = 0; i < lineSize; i++) {
            int lineHeight = heights.get(i);
            List<View> lineViews = this.views.get(i);
            int size = lineViews.size();
            for (int j = 0; j < size; j++) {
                View child = lineViews.get(j);
                ViewGroup.LayoutParams lp = child.getLayoutParams();
                if (lp.height == LayoutParams.MATCH_PARENT) {
                    int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
                    int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lineHeight);
                    child.measure(childWidthSpec, childHeightSpec);
                }
            }
        }
    }

    private void reflection() throws NoSuchFieldException {
        Class clazz = ViewGroup.class;
//        Field mGroupFlags = clazz.getDeclaredField("mGroupFlags");
//        mGroupFlags.setAccessible(true);
//        mGroupFlags.set(this,);
    }
}
