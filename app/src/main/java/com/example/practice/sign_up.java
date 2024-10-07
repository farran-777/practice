package com.example.practice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class sign_up extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText emailInput, passwordInput , Username;
    private Button signupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailInput = findViewById(R.id.email);  // Assuming these views are already defined in the XML layout
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
        // Query Firestore to find the user by email
        db.collection("Users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();

                        if (!querySnapshot.isEmpty()) {
                            // Get the first matching user (since email should be unique)
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                @SuppressLint("RestrictedApi") User user = document.toObject(User.class);

                                // Compare the entered password with the stored password
                                if (user.getPassword().equals(enteredPassword)) {
                                    // Successful login
                                    Toast.makeText(sign_up.this, "Login successful!", Toast.LENGTH_SHORT).show();

                                    // You can now proceed to the next activity, e.g.
                                    // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    // startActivity(intent);

                                } else {
                                    // Password doesn't match
                                    Toast.makeText(sign_up.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // No user found with the entered email
                            Toast.makeText(sign_up.this, "User not found!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Error while fetching data
                        Toast.makeText(sign_up.this, "Error fetching data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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