package com.example.advanced_ui.recycleview;

import android.view.View;

import java.util.Stack;

public class Recycler {

    // 回收池以栈的数据结构存储 stack数组

    private Stack<View>[] views;

    public Recycler(int count) {
        views = new Stack[count];
        for (int i = 0; i < count; i++) {
            views[i] = new Stack<View>();
        }
    }

    public View getRecyclerView(int type) {
        try {
            return views[type].pop();
        } catch (Exception e) {
            return null;
        }
    }

    public void addRecyclerView(View view, int type) {
        views[type].push(view);
    }
}
