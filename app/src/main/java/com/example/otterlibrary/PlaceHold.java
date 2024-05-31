package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityPlaceHoldBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.otterlibrary.databinding.ActivityPlaceHoldBinding;


public class PlaceHold extends AppCompatActivity {
    private ActivityPlaceHoldBinding binding;  // View Binding object
    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding object and set the content view
        binding = ActivityPlaceHoldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the content view to the binding's root

        // Initialize the database and populate initial data
        db = LibraryDatabase.getInstance(this);
        db.populateInitialData();

        //
        binding.compsciBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Computer Science");
            }
        });

        //
        binding.selfhelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Self-Help");
            }
        });

        // S
        binding.fantasyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity("Historical Fantasy");
            }
        });

        binding.exitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(PlaceHold.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }


    // Method to open the Library activity based on the genre
    private void openActivity(String genre) {
        if (db.bookDao().hasGenre(genre)) {
            Intent intent = new Intent(PlaceHold.this, ChooseBook.class);

            Bundle bundle = new Bundle();
            bundle.putString("myGenre", genre);  // Pass the genre in a bundle
            intent.putExtras(bundle);
            startActivity(intent);  // Start the new activity
        } else {
            binding.genre.setText("There is no book available in that genre. Please click Exit");  // Use the binding object to set text
            Toast.makeText(PlaceHold.this, "There is no book available in that genre", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this, MainActivity.class));  // Navigate to MainActivity if the genre doesn't exist
        }
    }
}