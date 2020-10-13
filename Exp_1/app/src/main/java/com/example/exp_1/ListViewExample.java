package com.example.exp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewExample extends AppCompatActivity {

    private List<Cooker> cookerList = new ArrayList<Cooker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_example);

        // initList();
        Cooker cooker1 = new Cooker("华农兄弟", R.mipmap.ic_launcher);
        cookerList.add(cooker1);
        Cooker cooker2 = new Cooker("王刚", R.mipmap.ic_launcher);
        cookerList.add(cooker2);
        Cooker cooker3 = new Cooker("姜老刀", R.mipmap.ic_launcher);
        cookerList.add(cooker3);
        Cooker cooker4 = new Cooker("贝爷", R.mipmap.ic_launcher);
        cookerList.add(cooker4);

        CookerAdapter cookerAdapter = new CookerAdapter(ListViewExample.this, R.layout.array_adapter_layout, cookerList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(cookerAdapter);
    }

}