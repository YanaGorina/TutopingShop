package com.example.tutoringshop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.tutoringshop.db.AppDatabase;
@Entity(tableName = AppDatabase.USER_TABLE) //at compile time it will create a table for User where # of columns = # of members
public class User {

    @PrimaryKey(autoGenerate = true)//whenever I enter a new User it will automatically increment userId
    private int mUserId; //unique number for each entry
    private String mUserName;
    private String mPassword;
    private int mAdmin; // 1 for Admin and 0 for Client
    private boolean mLoggedIn = false;

    public User(String userName, String password, int admin){
        mUserName = userName;
        mPassword = password;
        mAdmin = admin;

    }

    public boolean isLoggedIn() {
        return mLoggedIn;
    }

    public void setLoggedIn(boolean mLoggedIn) {
        this.mLoggedIn = mLoggedIn;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public int getAdmin() {
        return mAdmin;
    }

    public void setAdmin(int admin) {
        this.mAdmin = admin ;
    }
}
