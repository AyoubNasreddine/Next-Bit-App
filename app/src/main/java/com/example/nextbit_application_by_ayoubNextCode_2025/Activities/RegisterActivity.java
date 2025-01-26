package com.example.nextbit_application_by_ayoubNextCode_2025.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.User;
import com.example.nextbit_application_by_ayoubNextCode_2025.databinding.ActivityRegisterBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseDatabase database ;
    DatabaseReference ref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database =FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        binding.registerButtonRegisterActivity.setOnClickListener(view->{
            String username = binding.nameEditTextRegister.getText().toString();
            String email = binding.emailEditTextRegister.getText().toString();
            String password = binding.passwordEditTextRegister.getText().toString();
            String repPassword = binding.repeatedPasswordEditText.getText().toString();
            int age = Integer.parseInt(binding.ageEditText.getText().toString());
            if (password.equals(repPassword)) {
                if (username.isEmpty() || password.isEmpty() || age == 0) {
                    Toast.makeText(this, "please enter your data correctly", Toast.LENGTH_SHORT).show();
                }else {
                    if (username.length() >= 5) {
                        if (email.contains("@")&&email.contains(".com")){
                            if (password.length()>=8){
                                User user =new User(username,email,password,age);
                                Register(user);
                            }else{
                                Toast.makeText(this, "password length must be equal or greater than 8", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "incorrect email form", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "username must be greater than 4 characters", Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(this, "password must be equal repeated password", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void Register(User user){
        ref.child(user.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Toast.makeText(RegisterActivity.this, "username is already taken", Toast.LENGTH_SHORT).show();
                }else{
                    ref.child(user.getName()).setValue(user);
                    Toast.makeText(RegisterActivity.this, "register successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RegisterActivity.this, "something went wrong please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}