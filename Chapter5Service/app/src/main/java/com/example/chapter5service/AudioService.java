package com.example.chapter5service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

public class AudioService extends Service{
    MediaPlayer mp;
    @Override
    public void onCreate() {  //在开始服务时调用
        super.onCreate();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.white);
        mp.start();
    }
    @Override
    public void onDestroy() {  //在停止服务时调用
        super.onDestroy();
        mp.stop();
        if(mp!=null) mp=null;
    }
    @Override
    public IBinder onBind(Intent intent) {  //不可省略的生命周期方法
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
