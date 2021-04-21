package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    public static final String EXTRAS_FOOD_NO = "FOOD_NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodNo = getIntent().getIntExtra(EXTRAS_FOOD_NO, 0);
        Food food = Food.foods[foodNo];

        ImageView image = (ImageView) findViewById(R.id.food_image);
        image.setImageResource(food.getImageId());

        TextView name = (TextView) findViewById(R.id.food_name);
        name.setText(food.getName());

        TextView description = (TextView) findViewById(R.id.food_description);
        description.setText(food.getDescription());
    }
}
