package com.example.nextbit_application_by_ayoubNextCode_2025.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nextbit_application_by_ayoubNextCode_2025.Classes.User;
import com.example.nextbit_application_by_ayoubNextCode_2025.R;
import com.example.nextbit_application_by_ayoubNextCode_2025.databinding.ActivityCourseDetailsBinding;

public class CourseDetailsActivity extends AppCompatActivity {
    private ActivityCourseDetailsBinding binding;
    private boolean available=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle data = getIntent().getExtras();
        String description, name;
        int image;
        if (data != null) {
            name = data.getString("name");
            description = data.getString("description");
            available = data.getBoolean("available");
            image = data.getInt("image");
            binding.courseTitleTv.setText(name);
            binding.courseIvDetails.setImageResource(image);
            binding.courseTvDescription.setText(description);
        }
        binding.getStartedTv.setOnClickListener(view -> {
            if (available){
                /**
                 * go to courseChaptersActivity
                 * build a quiz system in courseChaptersActivity
                 */
            }else{
                Toast.makeText(this, "the course is not available yet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}