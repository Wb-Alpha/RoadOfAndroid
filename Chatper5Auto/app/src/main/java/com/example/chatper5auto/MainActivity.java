package com.example.chatper5auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;  //用于设防或取消
    private TextView textView2; //提示文本
    private String fileName = "anti_disturb_sms.txt";  //短信文本文件，可修改
    private String message;//短信内容
    private EditText editText;  //短信内容编辑文本框
    private Button button;
    Intent intent;
    //本应用只涉及创建3个权限数组
    private static String[] permissions = {"android.permission.READ_PHONE_STATE",
            "android.permission.SEND_SMS",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态申请权限
        List<String> mPermissionList = new ArrayList<>();  //存放未授权的权限
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        //Build.VERSION.SDK_INT表示Android设备的API版本；符号常量Build.VERSION_CODES.M=23，对应于Android 6.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {   //Android 6.0以下版本时
            fun();   //做该做的
        } else {
            if (mPermissionList.isEmpty()) {
                fun();  //应用已经获得权限时直接执行
            } else{
                //集中请求未授权的权限；将List转为数组
                String[] needPermissions = mPermissionList.toArray(new String[mPermissionList.size()]);
                //请求权限后执行回调方法
                ActivityCompat.requestPermissions(this, needPermissions, 1);
            }
        }
    }
    public void fun(){   //已经获得权限（对应已经安装并授权）或者动态授权（对应于首次安装）后执行的代码块
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView1.setOnClickListener(new View.OnClickListener() {  //监听：设置或取消防打扰
            public void onClick(View view) {
                if(Constant.flag) {
                    Constant.flag = false;   //取消防打扰
                    textView1.setText(R.string.ennable_anti_disturb);
                    textView1.setTextColor(Color.parseColor("#000000"));//关闭状态字体为黑色
                    textView2.setVisibility(View.INVISIBLE);editText.setVisibility(View.INVISIBLE);button.setVisibility(View.INVISIBLE); //隐藏
                } else {
                    Constant.flag = true;    //设置防打扰
                    textView1.setText(R.string.cancel_anti_disturb);
                    textView1.setTextColor(Color.parseColor("#ffcccc"));//开启状态时将文字颜色改为粉红色
                    textView2.setVisibility(View.VISIBLE);editText.setVisibility(View.VISIBLE);button.setVisibility(View.VISIBLE);  //可见
                    try{
                        message = buildSms(fileName);  //创建短信内容
                        Constant.anti_sms=message;   //也是服务程序里发送的短信内容
                        editText.setText(message);
                        button.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View arg0) {
                                if("".equals(editText.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "回复为空！请重设！", Toast.LENGTH_SHORT).show();
                                } else {
                                    message = editText.getText().toString();
                                    try {
                                        writeFile(fileName, message);
                                        Constant.anti_sms=message;
                                    }
                                    catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                        // 设置防打扰模式后，以“隐式+非绑定”方式服务调用
                        intent = new Intent("com.wzx.service.ACTION_MYSERVICE"); //隐式配置
                        intent.setPackage("com.example.example5_4");
                        startService(intent);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Override  //Android 6.0动态权限处理的接口回调方法
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "未授权，无法实现全部功能！", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                fun();   //全部授权时执行
        }
    }
    public String buildSms(String fileName) throws IOException {  //读取文件anti_disturb_sms.txt
        String res = "";
        try{
            String basePath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(basePath+"/"+fileName);
            if(!file.exists()) {  //回复短信文件不存在时
                file.createNewFile();
                res = "亲，现在不便接电话，请稍后联系！";  //首次安装使用时默认回复的短信内容，修改后写入SD卡
                writeFile(fileName,res);  //调用
            } else {
                FileInputStream fis = new FileInputStream(file);
                int length = fis.available();
                byte [] buffer = new byte[length];
                fis.read(buffer);
                //res = EncodingUtils.getString(buffer, "UTF-8");//API 21之后不用
                res=new String(buffer,"UTF-8");  //
                fis.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public void writeFile(String fileName ,String write_str) throws IOException { //创建短信文件
        String basePath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(basePath+"/"+fileName);
        Log.i("wzxtest",basePath+"/"+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = write_str.getBytes();
        fos.write(bytes);
        fos.close();
        Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
    }
}
