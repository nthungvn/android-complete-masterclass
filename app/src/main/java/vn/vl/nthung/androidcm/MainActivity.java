package vn.vl.nthung.androidcm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private EditText message;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.message);
        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(onUpdateClicked);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("message");
        ref.addValueEventListener(new OnDataChangeHandler());
    }

    private View.OnClickListener onUpdateClicked = view -> {
        ref.setValue(message.getText().toString());
        Toast.makeText(this, "Data is updated", Toast.LENGTH_LONG).show();
    };

    class OnDataChangeHandler implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            message.setText(snapshot.getValue(String.class));
            Toast.makeText(MainActivity.this, "Server data has been changed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}