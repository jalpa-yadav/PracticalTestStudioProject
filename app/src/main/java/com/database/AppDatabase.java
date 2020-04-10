package com.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


    @Database(entities = {User.class}, version = 1,exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {
        private static AppDatabase appDB;
        public static AppDatabase getInstance(Context c)
        {
            if (appDB == null)
                appDB = Room.databaseBuilder(c,AppDatabase.class, "practical_test").build();

            return appDB;
        }
        public abstract UserDao userDao();
    }

