package com.example.otterlibrary;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityLibraryBinding;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityLibraryBinding;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityLibraryBinding;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityLibraryBinding;

import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import java.util.Random;

public class Library extends AppCompatActivity {
    private ActivityLibraryBinding binding; // The binding class instance
    private LibraryDatabase db;
    private String passedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Database initialization
        db = LibraryDatabase.getInstance(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            passedString = extras.getString("myGenre", "");
        }

        // Retrieve a book by genre
        Book myBook = db.bookDao().findByGenre(passedString);
        if (myBook != null) {
            Toast.makeText(Library.this, myBook.getBookTitle(), Toast.LENGTH_SHORT).show();
            binding.bookToHold.setText(myBook.getBookTitle());
        } else {
            binding.bookToHold.setText("No book available for this genre.");
        }


        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = binding.libraryUsername.getText().toString();
                String passwordText = binding.libraryPassward.getText().toString();

                if (db.userDAO().existByName(usernameText) &&
                        passwordText.equals(db.userDAO().getPasswardByName(usernameText))) {

                    int reservationNumber = reservationNumber();
                    openActivity(usernameText, myBook.getBookTitle(), reservationNumber, passedString);
                } else {
                    Toast.makeText(Library.this, "Username or password is wrong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Library.this, MainActivity.class));
                }
            }
        });
    }

    public void openActivity(String user, String title, int reservationNumber, String genre) {
        Intent intent = new Intent(Library.this, CheckOutBook.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", user);
        bundle.putString("bookTitle", title);
        bundle.putInt("number", reservationNumber);
        bundle.putString("genre", genre);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public int reservationNumber() {
        Random r = new Random();
        return r.nextInt(900000) + 100000; // Random 6-digit reservation number
    }
}
