package com.example.chapter5service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class AudioTest1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_test2);

        final ServiceConnection conn = new ServiceConnection() {
            // 建立连接时
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 获取代理人对象
                AudioService1.PlayBinder playBinder = (AudioService1.PlayBinder) service;
                playBinder.MyMethod();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // 断开服务链接
            }
        };

        //Intent intent = new Intent(getApplicationContext(), AudioService1.class);    //绑定,显式调用
        Intent intent = new Intent("com.wust.wzx.MUSIC_PLAY_SERVICE");
        intent.setPackage("com.example.chapter5service");   // 绑定，隐式时需要指定应用的包名
        // 绑定服务
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
}