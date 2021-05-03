package vn.vl.nthung.androidcm;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private EditText name;
    private EditText contact;
    private ListView contactsList;
    private List<String> contactNames;
    private DatabaseReference parentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactNames = new ArrayList<>();
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        contactsList = findViewById(R.id.contact_list);

        ListAdapter contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        contactsList.setAdapter(contactAdapter);

        Button updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(onUpdateClicked);

        database = FirebaseDatabase.getInstance();
        parentRef = database.getReference("users");
        parentRef.addChildEventListener(new OnDataChangeHandler());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private View.OnClickListener onUpdateClicked = view -> {
        String inputName = name.getText().toString();
        DatabaseReference childRef = parentRef.child(inputName);
        childRef.child("name").setValue(name.getText().toString());
        childRef.child("contact").setValue(contact.getText().toString());
        Toast.makeText(this, "Data is updated", Toast.LENGTH_LONG).show();
    };

    class OnDataChangeHandler implements ChildEventListener {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Map<String, String> contact = (Map) snapshot.getValue();
            contactNames.add(contact.get("name"));
            Toast.makeText(MainActivity.this, "Server data has been changed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            Map<String, String> contact = (Map) snapshot.getValue();
            contactNames.removeIf((item) -> item.equals(contact.get("name")));
            Toast.makeText(MainActivity.this, "Server data has been changed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}