package vn.vl.nthung.androidcm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);

        Button loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(loginHandler);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private OnCompleteListener loginCompleteHandler = task -> {
        if (task.isSuccessful()) {
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener loginHandler = view -> {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (email.trim().length() == 0 || password.trim().length() == 0) {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginCompleteHandler);
    };
}