package com.example.chapter5service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AudioTest extends AppCompatActivity implements  View.OnClickListener{
    Intent intent;
    Button btn_play,btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_test);
        btn_play = findViewById(R.id.btn_play);btn_play.setOnClickListener(this);  //播放按钮
        btn_stop=findViewById(R.id.btn_stop);btn_stop.setOnClickListener(this);  //停止按钮
    }
    @Override
    public void onClick(View v) {
        intent=new Intent(this,AudioService.class);  //显式调用服务意图
        /*intent=new Intent("com.example.audio_service_test.MAS");//隐式调用时使用，需要意图过滤器
        intent.setPackage("com.example.example5_1"); //隐式调用时还需要指定应用的包名*/
        int id=v.getId();  //获取视图id
        switch (id){
            case R.id.btn_play:
                startService(intent);
                Toast.makeText(this, "音乐播放服务进行中...", Toast.LENGTH_SHORT).show();
                btn_stop.setEnabled(true);btn_play.setEnabled(false);
                break;
            case R.id.btn_stop:
                stopService(intent);
                btn_stop.setEnabled(false);btn_play.setEnabled(true);
        }
    }
    @Override
    protected void onDestroy() {  //考虑播放时按返回键
        super.onDestroy();
        if(intent!=null) stopService(intent); //停止服务
        finish();  //关闭活动
    }
}