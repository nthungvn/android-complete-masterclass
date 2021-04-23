package vn.vl.nthung.androidcm;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {
    private MenuListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        this.listener = (MenuListener) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListAdapter menuItems = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, Menu.items);
        setListAdapter(menuItems);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.onItemSelected(position);
        }
        super.onListItemClick(l, v, position, id);
    }

    public interface MenuListener {
        void onItemSelected(int itemId);
    }
}