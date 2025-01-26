package com.example.nextbit_application_by_ayoubNextCode_2025.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.User;
import com.example.nextbit_application_by_ayoubNextCode_2025.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseDatabase database;
    DatabaseReference ref;
    private final String myGitHubUrl = "https://github.com/AyoubNasreddine";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        binding.signInButton.setOnClickListener(view -> {
            String username = binding.userNameEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "please enter your data correctly", Toast.LENGTH_SHORT).show();
            } else {
                if (username.length()>=5) {
                    Login(username, password);
                } else {
                    Toast.makeText(this, "username must be greater than 4 characters", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.registerButton.setOnClickListener(view -> startActivity(new Intent(this, RegisterActivity.class)));
        binding.contactButton.setOnClickListener(view -> goToURL());
    }
    void goToURL() {
        try {
            Uri uri = Uri.parse("https://github.com/AyoubNasreddine");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    void Login(String username, String password) {
        ref.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        if (user.getName().equals(username) && user.getPassword().equals(password)) {
                            Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                            intent.putExtra("username",user.getName());
                            Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "account doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "something went wrong please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}