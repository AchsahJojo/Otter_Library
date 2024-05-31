package com.example.otterlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrary.databinding.ActivityTransactionBinding;

import java.util.ArrayList;
import java.util.List;

public class Transaction extends AppCompatActivity {
    private ActivityTransactionBinding binding;  // View Binding object
    private LibraryDatabase  db;
    private UserLogDAO userLogDao;
    private ReservationDAO reservationDAO;
    private List<UserLog> accountsList;
    private List<Reservation> reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding object and set the content view
        binding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the content view to the binding's root

        // Initialize the database and DAOs
        db = LibraryDatabase.getInstance(this);
        userLogDao = db.UserLogDao();
        reservationDAO = db.reservationDao();

        // Get data from the database
        accountsList = userLogDao.getAllLogs();
        reservationList = reservationDAO.getAllBookHold();

        // Set up the list view with an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, getData()
        );
        binding.listView.setAdapter(adapter);  // Use the binding object to reference the ListView

        // Set up the OK button's click listener
        binding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(AddNewBook.class);  // Navigate to NewBookPrompt activity
            }
        });
    }

    // Method to get data for the ListView
    private List<String> getData() {
        List<String> dataList = new ArrayList<>();
        for (UserLog account: accountsList) {
            dataList.add(account.toString());
        }
        for (Reservation reservation : reservationList) {
            dataList.add(reservation.toString());
        }
        return dataList;
    }

    // Method to open another activity
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}