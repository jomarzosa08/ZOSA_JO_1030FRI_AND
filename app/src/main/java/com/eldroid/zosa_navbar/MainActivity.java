package com.eldroid.zosa_navbar;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Set default fragment when the activity starts
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ToDoListFragment())
                    .commit();
        }

        // Set the listener for BottomNavigationView
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_calculator) {
                    navigateToFragment(new CalculatorFragment());
                    return true;
                } else if (id == R.id.nav_todolist) {
                    navigateToFragment(new ToDoListFragment());
                    return true;
                } else if (id == R.id.nav_profile) {
                    navigateToFragment(new ProfileFragment());
                    return true;
                }
                return false;
            }
        });
    }

    // Method to replace the current fragment with a new one
    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}


