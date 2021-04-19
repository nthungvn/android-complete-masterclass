package vn.vl.nthung.androidcm;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button switchButton = (Button) findViewById(R.id.switchButton);
        switchButton.setOnClickListener((View.OnClickListener) v -> {
            TextView textView = (TextView) findViewById(R.id.greeting);
            textView.setText("Button clicked");
        });
    }
}