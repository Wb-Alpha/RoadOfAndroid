package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

public class PlayMusic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        if (ActivityCompat.checkSelfPermission(PlayMusic.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayMusic.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else{
            fun();
        }
    }

    private void fun() {
        String basePath = Environment.getExternalStorageDirectory().getPath();
        String filePath = basePath+"/music/white.mp3";
        Log.i("wutest", filePath);
        MediaPlayer mp = new MediaPlayer();
        try{
            mp.setDataSource(filePath);
            mp.prepare();
            mp.start();
        }
        catch(IOException e){
            Log.i("wutest", "file path error or no permission of READ_EXTERNAL_STORAGE");
            e.printStackTrace();
        }
    }
}