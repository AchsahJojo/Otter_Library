package com.example.otterlibrary;

import androidx.room.*;

import java.util.List;
//Accounts DAO


@Dao
public interface UserDAO {
    @Insert
    void insertAll(User ... users);

    @Insert
    void addAccount(User users);
    @Query("SELECT * FROM users")
    int count();
    @Query("SELECT * FROM users")
    List<User> getAllAccounts();
    @Delete
    void delete(User users);
    @Query("SELECT * FROM users WHERE username = :username")
    User findByName(String username);

    @Query("SELECT * FROM users WHERE username = :username")
    boolean existByName(String username);

    @Query("SELECT passward FROM users WHERE username = :username")
    String getPasswardByName(String username);
}