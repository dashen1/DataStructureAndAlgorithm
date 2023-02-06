package com.example.advanced_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.advanced_ui.recycleview.CustomRecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    CustomRecyclerView recyclerView;
    int count = 500000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.table);
        recyclerView.setAdapter(new MyAdapter(this));
    }


    class MyAdapter implements CustomRecyclerView.Adapter {

        LayoutInflater inflater;
        private int height;

        public MyAdapter(Context context) {
            Resources resources = context.getResources();
            height = resources.getDimensionPixelSize(R.dimen.item_height);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View onCreateViewHolder(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "onCreateViewHolder");
            convertView = inflater.inflate(R.layout.item_image, parent, false);
            convertView.setTag(new Object());
            return convertView;
        }

        @Override
        public View onBinderViewHolder(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "onBinderViewHolder");
            TextView textView = convertView.findViewById(R.id.text1);
            textView.setText("第"+position+"行");
            return convertView;
        }

        @Override
        public int getItemViewType(int row) {
            if (row == 0) {
                return 1;
            }
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public int getHeight(int index) {
            return height;
        }
    }
}