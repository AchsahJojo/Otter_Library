package com.example.otterlibrary;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
//Books DAO
@Dao
public interface BookDAO {
    @Insert
    void insertAll(Book... books);
    @Insert
    void addBook(Book books);
    @Query("SELECT * FROM book ")
    List<Book> getAllBooks();
    @Delete
    void delete(Book Book);

    @Query("SELECT * FROM book WHERE genre = :genre")
    Book findByGenre(String genre);

    @Query("SELECT COUNT(*) FROM book")
    int count();
    @Query("SELECT *FROM book WHERE genre = :genreName")
    boolean hasGenre(String genreName);

    @Query("SELECT * FROM book WHERE Genre = :genre")
    List<Book> findAllByGenre(String genre);

    @Query("SELECT * FROM book WHERE `Books Title` = :title")
    Book findByTitle(String title); // This method returns a book object or null if not found

}