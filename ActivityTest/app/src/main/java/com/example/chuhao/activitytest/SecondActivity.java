package com.example.chuhao.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("extra_data");
        Button button1 = (Button) findViewById(R.id.button_2);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(SecondActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        Button button_return = (Button) findViewById(R.id.button_return);
        button_return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.putExtra("data_return", "HelloFirstActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
