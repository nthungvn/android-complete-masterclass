package vn.vl.nthung.androidcm;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class MenuFragment extends ListFragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListAdapter menuItems = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, Menu.items);
        setListAdapter(menuItems);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}