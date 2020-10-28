package com.example.chapter5service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    Intent intent;
    Button btn_play, btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);

        btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent  = new Intent(this, AudioService.class);
        int id = v.getId();
        switch (id){
            case R.id.btn_play:
                startService(intent);
                Toast.makeText(this, "音乐服务进行中", Toast.LENGTH_LONG);
                btn_stop.setEnabled(true);
                btn_play.setEnabled(false);
                break;

            case R.id.btn_stop:
                stopService(intent);
                btn_stop.setEnabled(false);
                btn_play.setEnabled(true);
        }
    }
}