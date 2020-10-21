package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


/*
接收MainActivity传递的Bundle数据
销毁前，向主Activity传递数据
 */
public class ThridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        String receiver = "接收的数据如下\n";
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");    // 获得捆绑数据
        receiver += "name:"+data.getString("name")+"\n";
        receiver += "age"+data.getString("age")+"\n";
        Toast.makeText(this, receiver, Toast.LENGTH_LONG).show();
        intent = new Intent();
        intent.putExtra("hello", "how are you");
        setResult(3, intent);
    }
}