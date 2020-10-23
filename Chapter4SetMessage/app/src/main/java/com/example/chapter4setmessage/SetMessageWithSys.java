package com.example.chapter4setmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SetMessageWithSys extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_message_with_sys);

        // Intent intent = new Intent();
        // intent.setAction(Intent.ACTION_SENDTO);
        // intent.setAction(Intent.ACTION_VIEW);
        // intent.setData(Uri.parse("sms:14718155972?body=手机短信测试"));
        Uri uri = Uri.parse("smsto:14718155972");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "手机短信测试");
        startActivity(intent);
    }
}