package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view) {
        TextView output = findViewById(R.id.bmiResult);
        TextView height = findViewById(R.id.yourHeight);
        TextView weight = findViewById(R.id.yourWeight);
        Float heightValue = Float.valueOf(height.getText().toString());
        Float weightValue = Float.valueOf(weight.getText().toString());
        Float bmiResult = heightValue / (weightValue * weightValue);
        output.setText(bmiResult.toString());
    }
}