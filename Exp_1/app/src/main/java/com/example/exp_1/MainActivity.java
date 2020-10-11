package com.example.exp_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.list_view1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ArrayAdapter.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "LoginSystemStart", Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View dialog = inflater.inflate(R.layout.dialog_view_layout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialog)
                        .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                /*
                                EditText username = findViewById(R.id.userEdit);
                                EditText password = findViewById(R.id.passwordEdit);
                                String name = username.getText().toString();
                                String pass = password.getText().toString();
                                System.out.println(name+" "+pass);

                                if (name.equals("admin") && pass.equals("123456")){
                                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "登陆失败，密码不正确", Toast.LENGTH_SHORT).show();
                                }*/
                            }
                        });
                builder.create().show();
            }
        });

    }
}