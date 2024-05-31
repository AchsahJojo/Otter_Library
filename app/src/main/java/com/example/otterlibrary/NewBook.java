package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityNewBookBinding;

public class NewBook extends AppCompatActivity {
    private ActivityNewBookBinding binding;
    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNewBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from the EditText fields using the binding object
                String title = binding.bookTitle.getText().toString();
                String author = binding.author.getText().toString();
                String genre = binding.genreText.getText().toString();

                if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                    Toast.makeText(NewBook.this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
                    return; // Return early if validation fails
                }

////                // Check for duplicate books by title
//                if (db.bookDao().findByTitle(title) != null) {
//                    Toast.makeText(NewBook.this, "This book already exists! Use a different title.", Toast.LENGTH_SHORT).show();
//                    return; // Return early if there's a duplicate
//                }

                openActivity(title, author, genre, Confirmation.class);


                // Call the method to open another activity, passing the book details
              //  openActivity(title, author, genre, Confirmation.class);
            }
        });
    }

    private void openActivity(String title, String author, String genre, Class<?> activityClass) {
        Intent i = new Intent(NewBook.this, activityClass);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("author", author);
        bundle.putString("genre", genre);
        i.putExtras(bundle);
        startActivity(i);
    }
}
