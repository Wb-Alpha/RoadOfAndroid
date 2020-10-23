package com.example.chapter4callphonewithsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        //显示用户数据的通用方式，根据用户数据类型打开相应的ACTIVITY
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("tel:12580"));
        startActivity(intent);
    }
}