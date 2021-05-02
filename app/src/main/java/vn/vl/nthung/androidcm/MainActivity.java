package vn.vl.nthung.androidcm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(onUpdateClicked);
    }

    private View.OnClickListener onUpdateClicked = view -> {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("message");
        EditText message = findViewById(R.id.message);
        ref.setValue(message.getText().toString());
        Toast.makeText(this, "Data is updated", Toast.LENGTH_LONG).show();
    };
}