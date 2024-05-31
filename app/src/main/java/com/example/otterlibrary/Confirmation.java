package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityConfimationBinding;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;


public class Confirmation extends AppCompatActivity {
    private ActivityConfimationBinding binding; // The binding class
    private LibraryDatabase db;
    private String titleString, authorString, genreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the view binding
        binding = ActivityConfimationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);

        // Retrieve data from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            titleString = extras.getString("title");
            authorString = extras.getString("author");
            genreString = extras.getString("genre");
        }

        // Set text values using view binding
        binding.titleText.setText(titleString);
        binding.authorText.setText(authorString);
        binding.genreText.setText(genreString);

        // Set click listener for confirm button
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book newBook = new Book(authorString, titleString, genreString);
                db.bookDao().addBook(newBook);

                Toast.makeText(Confirmation.this, "Add Book confirmed", Toast.LENGTH_SHORT).show();
                openActivity(MainActivity.class); // Navigate to MainActivity
            }
        });

        // Set click listener for edit button
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(NewBook.class); // Navigate to NewBook
            }
        });
    }

    // Navigate to another activity
    public void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(Confirmation.this, activityClass);
        startActivity(intent);
    }
}



