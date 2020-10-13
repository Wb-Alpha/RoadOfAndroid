package com.example.exp_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BookDetailFragment extends Fragment {
    public static final String ITEM_ID = "item_id";
    BookContent.Book book;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ITEM_ID)){
            book = BookContent.ITEMS_MAP.get(getArguments().getInt(ITEM_ID));
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.book_fragment, container, false);
        if (book != null){
            ((TextView)rootView.findViewById(R.id.book_list).setText(book.title);
            ((TextView)rootView.findViewById(R.id.book_info)).setText(book.desc);
        }
    }
}
