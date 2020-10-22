package com.example.chapter4permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MutiPermissionTest extends AppCompatActivity {

    private static String[] permission = {
        "android.permission.READ_PHONE_STATE",
            "android.permission.SEND_"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muti_permission_test);
        List<String> mPermissionList = new ArrayList<String>();
        for (int i = 0; i < permission.length; i++){
            if (ContextCompat.checkSelfPermission(this, permission[i])!= PackageManager.PERMISSION_GRANTED){
                mPermissionList.add(permission[i]);
            }
            
        }
    }
}