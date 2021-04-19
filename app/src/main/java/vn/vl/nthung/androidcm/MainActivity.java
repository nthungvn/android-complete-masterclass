package vn.vl.nthung.androidcm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = (AdapterView<?> listView, View view, int position, long id) -> {
            if (position == 0) {
                Intent intent = new Intent(this, FoodCategoryActivity.class);
                startActivity(intent);
            }
        };
        ListView listView = (ListView) findViewById(R.id.food_type);
        listView.setOnItemClickListener(itemClickListener);
    }
}
