package com.example.otterlibrary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReservationDAO {

    @Insert
    void insertAll(Reservation... reservations);

    @Insert
    void addHold(Reservation  reservations);

    @Query("SELECT * FROM reserved")
    int count();

    @Query("SELECT * FROM reserved")
    List<Reservation> getAllBookHold();
    @Delete
    void delete(Reservation  holdBookLogs);


}
