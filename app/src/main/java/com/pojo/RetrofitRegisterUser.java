package com.pojo;

import com.google.gson.annotations.SerializedName;

public class RetrofitRegisterUser {
    @SerializedName("id")
    private int id;
    @SerializedName("token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RetrofitRegisterUser{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
