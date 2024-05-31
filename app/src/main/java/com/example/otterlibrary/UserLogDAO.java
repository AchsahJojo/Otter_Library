package com.example.otterlibrary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//AccountLog DAO
@Dao
public interface UserLogDAO {
    @Insert
    void insertAll(UserLog ... userLog);

    @Insert
    void addAccountLog(UserLog  userLog);

    @Query("SELECT * FROM userLog")
    int count();

    @Query("SELECT * FROM userLog")
    List<UserLog> getAllLogs();
    @Delete
    void delete(UserLog  accountLog);
}