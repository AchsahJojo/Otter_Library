package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityChooseBookBinding;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChooseBook extends AppCompatActivity {
    private ActivityChooseBookBinding binding; // View binding instance
    private LibraryDatabase db;
    private String selectedGenre;
    private String selectedBookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = ActivityChooseBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db= LibraryDatabase.getInstance(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedGenre = extras.getString("myGenre", "");
        }

        // Retrieve books based on the selected genre
        List<Book> booksInGenre = db.bookDao().findAllByGenre(selectedGenre);

        // Extract book titles for the spinner
        List<String> bookTitles = new ArrayList<>();
        for (Book book : booksInGenre) {
            bookTitles.add(book.getBookTitle());
        }

        // Set up the spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookTitles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.bookSpinner.setAdapter(adapter);

        // Set the spinner's onItemSelectedListener
        binding.bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBookTitle = bookTitles.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedBookTitle = null;
            }
        });

        // Set the confirm button's click listener
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = binding.username.getText().toString();
                String passwordText = binding.password.getText().toString();

                if (db.userDAO().existByName(usernameText) &&
                        passwordText.equals(db.userDAO().getPasswardByName(usernameText))) {

                    // Prepare the intent for the Checkout activity
                    Intent intent = new Intent(ChooseBook.this, CheckOutBook.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", usernameText);
                    bundle.putString("bookTitle", selectedBookTitle);
                    bundle.putString("genre", selectedGenre);
                    bundle.putInt("number", reservationNumber());
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(ChooseBook.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseBook.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private int reservationNumber() {
        Random r = new Random();
        return r.nextInt(900000) + 100000; // Random 6-digit reservation number
    }
}
