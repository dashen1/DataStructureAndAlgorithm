package com.example.advanced_ui.hooker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.advanced_ui.R;

public class HookMainActivity extends AppCompatActivity {

    private static final String TAG = "HookMainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hook);
    }


    public void click1(View view) {
        Log.i(TAG, ""+ Build.VERSION.SDK_INT);
        HookHelper.hookIActivityManager();
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }
}
