package com.example.practice;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class User_Activity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private useradapter userAdapter;
    private List<modeluser> userList;
    public FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user1);
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("User")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();

                        if (!querySnapshot.isEmpty()) {
                            // Get the first matching user
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                modeluser user = document.toObject(modeluser.class);
                                userList.add(user);
                                userAdapter = new useradapter(userList);
                                recyclerView.setAdapter(userAdapter);
                            }
                        }
                        else {
                            // Handle the error
                        }

                    }

                });
    }
}