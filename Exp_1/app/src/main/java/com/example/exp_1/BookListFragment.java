package com.example.exp_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class BookListFragment extends ListFragment {
    public interface Callbacks{
        public void onItemSelected(Integer id);
    }

    private Callbacks callbacks;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, BookContent.ITEMS));
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)){
            throw new IllegalStateException("book");
        }
        callbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        super.onListItemClick(listView,view,position,id);
        callbacks.onItemSelected(BookContent.ITEMS.get(position).id);
    }
}
