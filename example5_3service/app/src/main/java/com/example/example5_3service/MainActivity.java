package com.example.example5_3service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    public static final String PACKAGE = "com.example.example5_3";
    public static final String ACTION = "com.example.services.MusicService";
    private boolean isBinded = false;
    private IRemoteService mServer;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
               mServer = IRemoteService.Stub.asInterface(service);
               isBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
            mServer = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}