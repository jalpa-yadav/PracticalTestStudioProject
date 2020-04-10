package com.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE email LIKE :email AND " +
            "phone_number LIKE :phone_number LIMIT 1")
    User findByName(String email, String phone_number);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}