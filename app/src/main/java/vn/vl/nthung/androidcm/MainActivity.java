package vn.vl.nthung.androidcm;

import android.content.Intent;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int IMAGE_PICKER_REQUEST = 1;
    private FirebaseDatabase database;
    private EditText name;
    private EditText contact;
    private ListView contactsList;
    private List<String> contactNames;
    private DatabaseReference parentRef;
    private StorageReference storageReference;

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

        Button logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(logoutHandler);

        Button uploadBtn = findViewById(R.id.upload_btn);
        uploadBtn.setOnClickListener(uploadHandler);

        storageReference = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        parentRef = database.getReference("users");
        parentRef.addChildEventListener(new OnDataChangeHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICKER_REQUEST && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            StorageReference uploadRef = storageReference.child("Photos").child(uri.getLastPathSegment());
            uploadRef.putFile(uri).addOnCompleteListener(uploadCompleteHandler);
        }
    }

    private View.OnClickListener onUpdateClicked = view -> {
        String inputName = name.getText().toString();
        String inputContact = contact.getText().toString();
        if (inputContact.trim().length() == 0 || inputName.trim().length() == 0) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference childRef = parentRef.child(inputName);
        childRef.child("name").setValue(inputName);
        childRef.child("contact").setValue(inputContact);
        Toast.makeText(this, "Data is updated", Toast.LENGTH_SHORT).show();
    };

    private View.OnClickListener logoutHandler = view -> {
        FirebaseAuth.getInstance().signOut();
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    };

    private View.OnClickListener uploadHandler = view -> {
        Intent imagePicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        imagePicker.addCategory(Intent.CATEGORY_OPENABLE);
        imagePicker.setType("image/jpeg");
        startActivityForResult(imagePicker, IMAGE_PICKER_REQUEST);
    };

    private final OnCompleteListener uploadCompleteHandler = task -> {
        Toast.makeText(this, "Image is uploaded", Toast.LENGTH_SHORT).show();
    };

    class OnDataChangeHandler implements ChildEventListener {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Map<String, String> contact = (Map) snapshot.getValue();
            contactNames.add(contact.get("name"));
            Toast.makeText(MainActivity.this, "Server data has been changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            Map<String, String> contact = (Map) snapshot.getValue();
            contactNames.removeIf((item) -> item.equals(contact.get("name")));
            Toast.makeText(MainActivity.this, "Server data has been changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    }
}