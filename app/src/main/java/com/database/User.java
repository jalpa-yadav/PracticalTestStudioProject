package com.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "email")
    public String email;
}
