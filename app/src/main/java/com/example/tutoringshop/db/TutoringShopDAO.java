package com.example.tutoringshop.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tutoringshop.User;

import java.util.List;

@Dao
public interface TutoringShopDAO { // create all operations we want to do on my database
    @Insert // I don't need to implement this method b/s @Insert forces Room to implement it
    void insert(User...users); // I just need to provide return type, method name, and parameters

    @Update
    void update(User...users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :userName")
    User getUserByUserName(String userName);

}
