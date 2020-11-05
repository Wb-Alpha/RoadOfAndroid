package com.example.chapter5service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_play_audio = findViewById(R.id.btn_audio_play);
        btn_play_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AudioTest.class);
                startActivity(intent);
            }
        });

        Button btn_play_audio_b = findViewById(R.id.btn_audio_play_b);
        btn_play_audio_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AudioTest1.class);
                startActivity(intent);
            }
        });
    }

}