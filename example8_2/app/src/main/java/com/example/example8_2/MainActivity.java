package com.example.example8_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase myDb;
    ListView listView;
    List<Map<String, Object>> listData;
    Map<String, Object> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"内容提供者已经创建！", Toast.LENGTH_SHORT).show();

        dbHelper = new DbHelper(this, "test.db", null, 1);
        myDb = dbHelper.getWritableDatabase();
        Cursor cursor = allQuery();
        if (cursor.getCount() == 0){
            insertInfo("张三", 20);
            insertInfo("李四", 21);
            cursor = allQuery();
        }
        listView = findViewById(R.id.listView);
        listData = new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()){
            long id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            listItem = new HashMap<String , Object>();
            listItem.put("_id", id);
            listItem.put("name", name);
            listItem.put("age", age);
            listData.add(listItem);
        }
        SimpleAdapter listAdapter = new SimpleAdapter(this,
                listData,
                R.layout.list_item,
                new String[]{"_id", "name", "age"},
                new int[]{R.id.tv_id, R.id.tvname, R.id.tvage});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.tvname);
                Toast.makeText(MainActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    Cursor allQuery(){
        return myDb.rawQuery("select * from friends", null);
    }

    void insertInfo(String name, int age){
        myDb = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        long rowid = myDb.insert(DbHelper.TB_NAME, null, values);
        if (rowid == -1)
            Log.i("myDbDemo", "数据插入失败");
        else
            Log.i("myDbDemo", "数据插入成功"+rowid);
    }
}