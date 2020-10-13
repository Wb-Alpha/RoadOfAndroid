package com.example.exp_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements BookListFragment.Callbacks{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.cooker_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewExample.class);
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

        /*
        getSupportActionBar().hide();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.option_menu);
        toolbar.setOnMenuItemClickListener(new OptionMenuClickListener());
        */
    }

    //创建OptionMenu菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.option_menu,menu);  //第一个传入的参数是你创建的menu的名字
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()){
            case R.id.help:
                Toast.makeText (this,"帮助",Toast.LENGTH_LONG).show ();
                break;
            case  R.id.about:
                Toast.makeText (this,"关于",Toast.LENGTH_LONG).show ();
                break;
            case  R.id.option:
                Toast.makeText (this,"系统设置",Toast.LENGTH_LONG).show ();
                break;
            case  R.id.exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onItemSelected(Integer id) {
        Bundle bundle = new Bundle();
        bundle.putInt(BookDetailFragment.ITEM_ID, id);
        BookDetailFragment fragment = new BookDetailFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.book_info,fragment).addToBackStack(null).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class OptionMenuClickListener implements Toolbar.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.about:
                    Toast.makeText(MainActivity.this, "打开“关于”", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.help:
                    Toast.makeText(MainActivity.this, "打开“帮助”", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.option:
                    Toast.makeText(MainActivity.this, "打开“系统设置”", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    }

}
