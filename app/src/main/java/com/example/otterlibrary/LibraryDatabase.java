package com.example.otterlibrary;


import android.content.Context;
import android.media.tv.TableRequest;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Book.class, UserLog.class, Reservation.class},version = 9, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract BookDAO bookDao();
    public abstract UserLogDAO UserLogDao();
    public abstract ReservationDAO reservationDao();




    public static LibraryDatabase libraryDatabase;
    public static synchronized LibraryDatabase getInstance(Context context){
        if(libraryDatabase== null){
            libraryDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class, "LibraryDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return libraryDatabase;

    }
    public void populateInitialData() {
        runInTransaction(() -> {

            if (bookDao().count() == 0) {
                bookDao().addBook(
                        new Book("Marcus Aurelius", "Meditations ", "Self-Help"));

                bookDao().addBook(
                        new Book(" Rainer Maria Rilke", "Letters to a Young Poet", "Self-Help"));
                bookDao().addBook(
                        new Book("Madeline Miller", "Circe ", "Historical Fantasy"));
                bookDao().addBook(
                        new Book("Anita Faul ", "Intro to Machine Learning ", "Computer Science"));


            }
            if (userDAO().count() == 0){
                userDAO().addAccount(
                        new User("hShuard","m@thl3t3"));
                userDAO().addAccount(
                        new User("bMishra","bioN@no"));
                userDAO().addAccount(
                        new User("shirleyBee","carmel2Chicago"));

            }
        });
    }


}