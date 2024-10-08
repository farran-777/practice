package com.example.practice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class sign_up extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private EditText emailInput, passwordInput, Username;
    private Button signupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        signupButton = findViewById(R.id.signUpButton);
        Username = findViewById(R.id.username);

        signupButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String enteredPassword = passwordInput.getText().toString().trim();
            String username = Username.getText().toString().trim();

            if (!email.isEmpty() && !enteredPassword.isEmpty()) {
                handleLogin(email, enteredPassword);
            } else {
                Toast.makeText(sign_up.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLogin(String email, String enteredPassword) {
        mAuth.signInWithEmailAndPassword(email, enteredPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(sign_up.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(sign_up.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public EditText getUsername() {
        return Username;
    }

    public void setUsername(EditText username) {
        this.Username = username;
    }
}
