package com.example.ui_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.item, list);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);

    }

    private void initList() {
        for (int i = 0; i < 100; i++) {
            Item item = new Item("Item " + i, R.mipmap.ic_launcher);
            list.add(item);
        }
    }
}