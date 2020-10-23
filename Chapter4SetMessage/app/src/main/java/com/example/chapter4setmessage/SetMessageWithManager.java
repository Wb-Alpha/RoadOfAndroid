package com.example.chapter4setmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SetMessageWithManager extends AppCompatActivity {
    // 定义危险权限数组
    static String[] permissions = {"android.permissions.SEND_SMS", "android.permissions.READ_PHONE_STATE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_message_with_manager);

        List<String> mPermissionList = new ArrayList<>();
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++){
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED){
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()){
            fun();
        }
        else{
            // 集中请求未授权的权限；得到List对象对应的数组对象
            String[] needPermissions = mPermissionList.toArray(new String[mPermissionList.size()]);
            ActivityCompat.requestPermissions(this, needPermissions, 1);
        }
    }

    private void fun() {
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_phone = findViewById(R.id.et_phone);
                EditText et_context = findViewById(R.id.et_content);
                // 获取短信管理器
                SmsManager smsManager = SmsManager.getDefault();
                // 短信内容分段
                List<String> list = smsManager.divideMessage(et_context.getText().toString());
                // 短信内容分段
                for (String sms:list){
                    smsManager.sendTextMessage(et_phone.getText().toString(),null,sms,null,null);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                for (int i = 0; i < grantResults.length; i++){
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "权限不足", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    fun();
                }
        }
    }
}