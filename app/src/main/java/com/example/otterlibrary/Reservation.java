package com.example.otterlibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName =  "reserved")
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Transaction type")
    private String type;
    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "Reservation Number")
    private int reservationNumber;

    public Reservation(String type, String username, int reservationNumber) {
        this.type = type;
        this.username = username;
        this.reservationNumber = reservationNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String toString() {
        return "Transaction Type: " + type + "\n"+
                "Username: " + username + "\n" +
                "Reservation Number " + reservationNumber;
    }
}

