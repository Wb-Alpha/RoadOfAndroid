package com.example.exp_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.List;

public class CookerAdapter extends ArrayAdapter<Cooker> {
    private int layoutId;

    public CookerAdapter(Context context, int layoutId, List<Cooker> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cooker cooker = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_img);
        TextView textView = (TextView) view.findViewById(R.id.item_text);
        imageView.setImageResource(cooker.getImgId());
        textView.setText(cooker.getName());

        return view;
    }
}
