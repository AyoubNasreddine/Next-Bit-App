package com.example.nextbit_application_by_ayoubNextCode_2025.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nextbit_application_by_ayoubNextCode_2025.Fragments.CoursesFragment;
import com.example.nextbit_application_by_ayoubNextCode_2025.Fragments.HomeFragment;
import com.example.nextbit_application_by_ayoubNextCode_2025.Fragments.ProfileFragment;
import com.example.nextbit_application_by_ayoubNextCode_2025.Fragments.SettingsFragment;
import com.example.nextbit_application_by_ayoubNextCode_2025.databinding.ActivityDashboardBinding;
import com.example.nextbit_application_by_ayoubNextCode_2025.R;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            username=extras.getString("username");
            Toast.makeText(this, "Welcome "+username, Toast.LENGTH_SHORT).show();
        }
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (menuItem.getItemId()==R.id.courses) {
                replaceFragment(new CoursesFragment());
            } else if (menuItem.getItemId()==R.id.profile) {
                replaceFragment(new ProfileFragment());
            } else if (menuItem.getItemId()==R.id.settings) {
                replaceFragment(new SettingsFragment());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.fragmentContainerView,fragment);
        ft.commit();
    }
}