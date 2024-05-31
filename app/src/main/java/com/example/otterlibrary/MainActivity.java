package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;  // View Binding object
    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding object and set the content view
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the content view to the binding's root

        // Initialize the database and populate initial data
        db = LibraryDatabase.getInstance(this);
        db.populateInitialData();

        // Set up the click listener for the Create Account button
        binding.buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(CreateAcc.class);  // Navigate to CreateAccount activity
            }
        });

        // Set up the click listener for the Place Hold button
        binding.buttonPlaceHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(PlaceHold.class);  // Navigate to PlaceHold activity
            }
        });

        // Set up the click listener for the Manage System button
        binding.buttonManageSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(ManageSystem.class);  // Navigate to ManageSystem activity
            }
        });
    }

    // Method to open another activity
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);  // Start the new activity
    }
}