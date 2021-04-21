package vn.vl.nthung.androidcm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FoodCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, Food.foods);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listview, View view, int position, long id) {
        super.onListItemClick(listview, view, position, id);
        Intent intent = new Intent(this, FoodActivity.class);
        intent.putExtra(FoodActivity.EXTRAS_FOOD_NO, position);
        startActivity(intent);
    }
}
