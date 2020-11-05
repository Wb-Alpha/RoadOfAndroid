package com.example.chapter5service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;


// 以绑定服务的方式播放音频
public class AudioService1 extends Service {
    MediaPlayer mp;

    public AudioService1() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return new PlayBinder();
    }

    @Override
    public void onDestroy(){
        if (mp != null){
            mp.stop();
            mp.release();
        }
        super.onDestroy();
    }

    class PlayBinder extends Binder{
        public void MyMethod(){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.white);
            mp.start();
        }
    }
}
