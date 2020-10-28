package com.example.chapter5service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class AudioService extends Service {

    MediaPlayer mp;

    // 在开始服务的时候调用
    @Override
    public void onCreate(){
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.white);
        mp.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 在结束服务时调用
    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.stop();
        if (mp != null){
            mp = null;
        }
    }
}
