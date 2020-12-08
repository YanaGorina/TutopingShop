package com.example.tutoringshop.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tutoringshop.User;

@Database(entities = {User.class},version = 3) // entities = tables that I want to put in my DB
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "USER_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";

    public abstract TutoringShopDAO getTutoringShopDAO();//this method used to access my DAO object, Room takes care for the code
}
