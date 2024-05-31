package com.example.otterlibrary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//AccountLog
@Entity(tableName = "userLog")
public class UserLog {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "Transaction Type")
    private String type;

    public UserLog(String username, String type) {
        this.username = username;
        this.type = type;

    }

    @Override
    public String toString() {
        return "Transaction type : " + type + "\n"+
                "Customer’s username: " +  username ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

