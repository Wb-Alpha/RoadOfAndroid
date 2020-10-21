package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.i("wutest", "第二个Activity准备销毁....");
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("wutest", "第二个Activity已经销毁");
        // 当前Activity销毁后返回父Activity
        // 如果在主Activity中使用finish()方法，将会直接退出应用，等同于返回键
    }
}