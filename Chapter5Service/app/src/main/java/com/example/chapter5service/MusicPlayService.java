package com.example.chapter5service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MusicPlayService extends Service {
    private MediaPlayer mPlayer;

    public MusicPlayService() {
    }

    IBinder iBinder = new IRemoteService.Stub() {
        @Override
        public void play() throws RemoteException {
            if (mPlayer.isPlaying()){
                mPlayer.start();
                Log.d("测试", "play ");
            }
        }

        @Override
        public void stop() throws RemoteException {
            if (mPlayer.isPlaying()){
                mPlayer.stop();
                Log.d("测试","stop");
                try{
                    mPlayer.prepare();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                mPlayer.seekTo(0);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(this, "服务已绑定", Toast.LENGTH_LONG).show();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        if (mPlayer == null){
            mPlayer.stop();
            mPlayer.release();
            Log.d("测试", "onUnbind");
        }
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if (mPlayer == null){
            mPlayer = MediaPlayer.create(this, R.raw.white);
            mPlayer.setLooping(true);
            Log.d("测试", "created");
        }
    }
}
