package com.example.final_exam1;

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

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.ysdy");
                startActivityForResult(intent, 2);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ThirdActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "张三");
                bundle.putInt("age", 20);
                intent.putExtra("data", bundle);
                startActivityForResult(intent, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "从第"+requestCode+"个Activity返回", Toast.LENGTH_LONG).show();
        if (requestCode == 3){
            String string = data.getStringExtra("hello");
            Toast.makeText(this, string, Toast.LENGTH_LONG).show();
        }
    }
}