package vn.vl.nthung.androidcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startService = findViewById(R.id.start_service);
        startService.setOnClickListener((view) -> {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        });
    }
}