package com.example.advanced_ui.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.advanced_ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * 自定义控件
 * 适配器：将UI和控件滑动业务逻辑代码解耦
 */

public class CustomRecyclerView extends ViewGroup {

    private static final String TAG = "CustomRecyclerView";

    private boolean isNeedRelayout;
    private List<View> viewList;// 缓存当前显示的view
    private Adapter adapter;


    private int[] heights;
    private int rowCount;
    private int width;
    private int height;

    private Recycler recycler;

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        isNeedRelayout = true;
        this.viewList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure 执行");
    }

    // changed 父容器变化
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout 执行");
        if (isNeedRelayout || changed) {
            isNeedRelayout = false;
            viewList.clear();
            removeAllViews();
            if (adapter != null) {
                rowCount = adapter.getCount();
                heights = new int[rowCount];
                long start = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                Log.d(TAG, "start time:"+start);
                for (int i = 0; i < rowCount; i++) {
                    heights[i] += adapter.getHeight(i);
                    // 实例化布局 怎么摆放 摆放多少个 确定第一屏加载的数据
                }
                long end = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                Log.d(TAG, "end time:"+end);
                Log.d(TAG, "total time:"+ (end-start));
                width = r - l;
                height = b - t;
                int top = 0;
                int bottom;
                for (int i = 0; i < rowCount && top < height; i++) {
                    bottom = top + heights[i];
                    View view = makeAndSetUp(i, 0,top,  width, bottom);
                    viewList.add(view);
                    addView(view);
                    top = bottom;
                    Log.d(TAG, "addView end");
                }
            }
        }
    }

    private View makeAndSetUp(int index, int l, int t, int r, int b) {
        View view = obtain(index, r - l, b - t);
        view.layout(l, t, r, b);
        return view;
    }

    private View obtain(int row, int width, int height) {
        int type = adapter.getItemViewType(row);
        View recyclerView = recycler.getRecyclerView(type);
        View view = null;
        Log.d(TAG, "recyclerView is null:"+recyclerView);
        if (recyclerView == null) {
            view = adapter.onCreateViewHolder(row, null, this);
            if (view == null) {
                throw new RuntimeException("onCreateViewHolder 必须初始化");
            }
        } else {
            Log.d(TAG, "obtain onBinderViewHolder");
            view = adapter.onBinderViewHolder(row, recyclerView, this);
        }
        if (view == null) {
            throw new RuntimeException("convertView  must not null");
        }
        // tag值 填充 移除
        view.setTag(R.id.tag_type_view, type);
        // 测量
        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        //addView(view,0);
        Log.d(TAG, "addView end");
        return view;
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        int type = (int) view.getTag(R.id.tag_type_view);
        recycler.addRecyclerView(view, type);

    }

    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            this.adapter = adapter;
            isNeedRelayout = true;
            recycler = new Recycler(adapter.getViewTypeCount());
        }
    }

    public interface Adapter{
        View onCreateViewHolder(int position, View convertView, ViewGroup parent);
        View onBinderViewHolder(int position, View convertView, ViewGroup parent);

        int getItemViewType(int row);
        int getViewTypeCount();
        int getCount();

        int getHeight(int index);
    }
}
