package com.example.example8_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContentResolver contentResolver;
    private SimpleAdapter listAdapter;
    Cursor cursor;
    private HashMap<String, String> item;
    private List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        else{
            fun();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                fun();
            }
            else{
                Toast.makeText(this,"权限不足", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void fun() {
        listView = findViewById(R.id.listView);
        data = new ArrayList<Map<String, String>>();
        // 获取手机联系人的URI
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        // 通过内容解析器使用抽象类android,content.ContentResolver提供了query()等方法
        cursor = getContentResolver().query(uri, null, null, null, null);
        TextView textView = findViewById(R.id.textView);
        textView.setText("读取到"+cursor.getCount()+"个联系人");
        while (cursor.moveToNext()){
            // 先获取联系人id字段的索引号后再获取id值
            int idFieldIndex = cursor.getColumnIndex("_id");
            int id = cursor.getInt(idFieldIndex);

            // 先获取联系人姓名字段再获取姓名
            int nameFileIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameFileIndex);

            int numCountFieldIndex = cursor.getColumnIndex("display_name");
            int numCount = cursor.getInt(numCountFieldIndex);
            String phoneNumber = "";
            if (numCount > 0){
                Cursor phonecursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                        new String[]{Integer.toString(id)}, null);
                if (phonecursor.moveToFirst()){
                    int numFieldIndex = phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    phoneNumber = phonecursor.getString(numFieldIndex);
                }
            }
            item = new HashMap<String, String>();
            item.put("name", name);
            item.put("phoneNumber", phoneNumber);
            data.add(item);
        }

        listAdapter = new SimpleAdapter(this,data,
                android.R.layout.simple_list_item_2,
                new String[]{"name","phoneNumber"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(listAdapter);
    }
}