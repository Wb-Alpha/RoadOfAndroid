package com.example.chapter4camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else{
            fun();
        }
    }

    private void fun() {
        pic = findViewById(R.id.pic);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    // 调用系统相机程序
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String basePath = Environment.getExternalStorageDirectory().getPath();
        String filePath = basePath+"/myImg";

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                FileOutputStream fos = null;
                File file = new File(filePath);
                file.mkdir();
                String fileName = filePath+"/11.jpg";
                try {
                    fos = new FileOutputStream(fileName);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                   try{
                        fos.flush();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    pic.setImageBitmap(bitmap);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        switch (requestCode){
            case 1:
                if (grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    fun();
                }
                else{
                    Toast.makeText(this, "没有SD卡读取权限", Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }
}