package com.example.ui_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by baniel on 1/19/17.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    private int layoutId;

    public ItemAdapter(Context context, int layoutId, List<Item> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
        TextView textView = (TextView) view.findViewById(R.id.item_text);
        imageView.setImageResource(item.getImgId());
        textView.setText(item.getName());

        return view;
    }
}

