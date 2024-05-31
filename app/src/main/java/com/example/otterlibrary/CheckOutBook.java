package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityCheckOutBookBinding;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;


import java.util.Random;

public class CheckOutBook extends AppCompatActivity {
    private ActivityCheckOutBookBinding binding; // View binding instance
    private LibraryDatabase db;
    private String customerString, bookTitleString, genre;
    private int reservationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityCheckOutBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the database
        db = LibraryDatabase.getInstance(this);
        db.populateInitialData();

        // Retrieve data from the intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            customerString = bundle.getString("username", "");
            bookTitleString = bundle.getString("bookTitle", "");
            reservationNumber = bundle.getInt("number", 0);
            genre = bundle.getString("genre", "");
        }

        // Set text for TextViews
        binding.customer.setText("Customer username: " + customerString);
        binding.bookTitle.setText("Book Title: " + bookTitleString);
        binding.reservation.setText("Reservation Number: " + reservationNumber);

        // Set click listener for the confirm button
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Confirm the hold and perform database operations
                Toast.makeText(CheckOutBook.this, "The hold is confirmed", Toast.LENGTH_SHORT).show();

                Reservation hold = new Reservation("Place Hold", customerString, reservationNumber);
                db.bookDao().delete(db.bookDao().findByGenre(genre)); // Delete the book from the genre
                db.reservationDao().addHold(hold); // Add to the hold book log

                // Navigate to MainActivity
                openActivity(MainActivity.class);
            }
        });
    }

    // Method to navigate to a different activity
    public void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(CheckOutBook.this, activityClass);
        startActivity(intent);
    }

    // Method to generate a reservation number
    private int reservationNumber() {
        Random r = new Random();
        return r.nextInt(900000) + 100000; // Generate a random 6-digit reservation number
    }
}

