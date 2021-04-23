package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MenuFragment.MenuListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemSelected(int itemId) {
        Bundle args = new Bundle();
        args.putInt(MenuDetailFragment.MENU_ID, itemId);
        MenuDetailFragment detailFragment = new MenuDetailFragment();
        detailFragment.setArguments(args);
        FragmentTransaction tm = getSupportFragmentManager().beginTransaction();
        tm.replace(R.id.menu_detail_fragment, detailFragment);
        tm.addToBackStack(null);
        tm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        tm.commit();
    }
}