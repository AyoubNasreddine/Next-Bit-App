package com.example.nextbit_application_by_ayoubNextCode_2025.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.nextbit_application_by_ayoubNextCode_2025.R;
import com.example.nextbit_application_by_ayoubNextCode_2025.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.getStartedButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }
}