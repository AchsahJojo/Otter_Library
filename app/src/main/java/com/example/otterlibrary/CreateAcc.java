package com.example.otterlibrary;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityCreateAccBinding;

public class CreateAcc extends AppCompatActivity {
    private ActivityCreateAccBinding binding;  // View Binding object
    private LibraryDatabase db;
    private int duplicateAccAttemptCount=0;
    private int invalidUsernameAttemptCount=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding object for this activity
        binding = ActivityCreateAccBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the content view to the root of the binding

        // Initialize database
        db = LibraryDatabase.getInstance(this);

        // Set default message
        binding.result.setText("Please Enter Username and Password");

        // Set up the create account button's click listener
        binding.createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.enterUsername.getText().toString();
                String passward = binding.enterPassward.getText().toString();

                if (db.userDAO().existByName(user)) {
                    binding.result.setText("Sorry, Account already exists, please use a different Username");
                    Toast.makeText(CreateAcc.this, "Account already exists", Toast.LENGTH_SHORT).show();
                    duplicateAccAttemptCount++;
                    resetFields();  // Clear fields
                    if(duplicateAccAttemptCount>=2){
                        redirectToMainMenu();
                    }
                } else if (user.equals("!admin2")) {
                    binding.result.setText("Invalid username, can't use !admin2");
                    Toast.makeText(CreateAcc.this, "Invalid username", Toast.LENGTH_SHORT).show();
                    invalidUsernameAttemptCount++;
                    resetFields();
                    if(invalidUsernameAttemptCount>=2){
                        redirectToMainMenu();
                    }
                } else if (user.isEmpty() || passward.isEmpty()) {
                    binding.result.setText("Username and/or Password can't be blank");
                    Toast.makeText(CreateAcc.this, "Username and/or Password can't be blank", Toast.LENGTH_SHORT).show();
                    resetFields();
                } else {
                    binding.result.setText(user + " has successfully created");
                    User newAccount = new User(user, passward);
                    db.userDAO().addAccount(newAccount);
                    Toast.makeText(CreateAcc.this, "Account Created successfully", Toast.LENGTH_SHORT).show();

                    // Log the account creation
                    UserLog myaccLog = new UserLog(user, "new Account");
                    db.UserLogDao().addAccountLog(myaccLog);

                    openMainActivity();  // Navigate to MainActivity
                }
            }
        });
        binding.backToMainMenuButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redirectToMainMenu();
                    }
                }
        );
    }

    // Method to reset fields (clears the text)
    private void resetFields() {
        binding.enterUsername.setText("");
        binding.enterPassward.setText("");
    }
    private void redirectToMainMenu() {
        // Redirect to the main menu
        Intent intent = new Intent(CreateAcc.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Method to navigate to the MainActivity
    private void openMainActivity() {
        Intent intent = new Intent(CreateAcc.this, MainActivity.class);
        startActivity(intent);
    }
}