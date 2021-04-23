package vn.vl.nthung.androidcm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuDetailFragment extends Fragment {
    public static String MENU_ID = "menu_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(MENU_ID)) {
            int menuId = getArguments().getInt(MENU_ID);
            Menu selectedMenu = Menu.items[menuId];
            TextView name = getView().findViewById(R.id.name);
            name.setText(selectedMenu.getName());
            TextView description = getView().findViewById(R.id.description);
            description.setText(selectedMenu.getDescription());
        }
    }
}