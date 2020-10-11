package com.example.exp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapter extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        listView = findViewById(R.id.listView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.button1:
                List<String> data1 = new ArrayList<String>();
                data1.add("烧鸭");
                data1.add("烧鸡腿");
                data1.add("香酥鸡");
                final ArrayAdapter adapter1 = new ArrayAdapter(

                );
                // listView.setAdapter(adapter1);
        }

    }
}