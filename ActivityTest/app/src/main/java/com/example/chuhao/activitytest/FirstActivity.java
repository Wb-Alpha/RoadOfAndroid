package com.example.chuhao.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        // 让按钮对点击进行响应
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(FirstActivity.this, "You Click Button One", Toast.LENGTH_SHORT).show();
            }
        });

        // 返回功能的实现（通过完成一个事件）
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               finish();
           }
        });

        // 显示继承
        Button button_intent = (Button) findViewById(R.id.button_intent);
        button_intent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        // 隐式继承
        Button button_im_intent = (Button) findViewById(R.id.button_imintent);
        button_im_intent.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent("com.example.chuhao.activitytest.ACTION_START");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
            }
        });

        // 通过隐式intent启动系统浏览器并打开百度
        Button button_web = (Button) findViewById(R.id.button_web);
        button_web.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        // 点击跳转到电话界面并且默认拨打110
        Button button_tel = (Button) findViewById(R.id.button_tel);
        button_tel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:120"));
                startActivity(intent);
            }
        });

        // 打开一个活动并且向这个活动传递参数
        Button button_send_para = (Button) findViewById(R.id.button_sendpara);
        button_send_para.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    // 创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // 设置菜单选项被点击的时候的响应时间
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "You Click Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You Click Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
