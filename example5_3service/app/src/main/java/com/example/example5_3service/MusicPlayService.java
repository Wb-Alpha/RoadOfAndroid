package com.example.example5_3service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MusicPlayService extends Service {
    private MediaPlayer mediaPlayer;

    IBinder iBinder = new IRemoteService.Stub() {
        @Override
        public void stop() throws RemoteException {
            if (!mediaPlayer.isPlaying()){
                mediaPlayer.start();
                Log.d("测试","play");
            }
        }

        @Override
        public void play() throws RemoteException {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                Log.d("测试","stop");
                try{
                    mediaPlayer.prepare();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                mediaPlayer.seekTo(0);
            }
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.white);
            mediaPlayer.setLooping(true);
            Log.d("测试","created");
        }
    }

    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(this, "服务已绑定！", Toast.LENGTH_LONG).show();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            Log.d("测试","onUnbind");
        }
        return super.onUnbind(intent);
    }
}
