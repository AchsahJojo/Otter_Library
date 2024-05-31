package com.example.otterlibrary;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityManageSystemBinding;

public class ManageSystem extends AppCompatActivity {
    private ActivityManageSystemBinding binding;  // View Binding object
    private int failedAttemptCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding object for this activity
        binding = ActivityManageSystemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the content view to the binding's root

        // Set up the submit button's click listener
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.librarianUsername.getText().toString();
                String passward = binding.librarianPassward.getText().toString();

                if (!username.equals("!admin2") && !passward.equals("!admin2")) {
                    Toast.makeText(ManageSystem.this,"Invalid Username or Password, Please Try Again!",Toast.LENGTH_SHORT).show();
                    failedAttemptCount++;
                    onResume();
                    if(failedAttemptCount>=2){
                        Intent intent=new Intent(ManageSystem.this,MainActivity.class);
                        startActivity(intent);
                        finish(); // Optional: close the current activity
                    }
                } else {
//                    Toast.makeText(ManageSystem.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
//                    onResume();  // Reset the fields
//                    finish();  // End this activity
                    openActivity(Transaction.class);  // Navigate to Transaction activity
                }
            }
        });
    }

    // Method to open another activity
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        // Clear the text fields when the activity is resumed
        binding.librarianUsername.setText("");
        binding.librarianPassward.setText("");
        super.onResume();
    }
}