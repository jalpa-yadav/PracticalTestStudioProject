package com.retrofit_calls;

import com.pojo.RetrofitRegisterUser;
import com.pojo.RetrofitSingleUser;
import com.pojo.RetrofitUserList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("/api/users")
    Call<RetrofitUserList> getUserList(@Query("page") String pageNo);
    @GET
    Call<RetrofitSingleUser> getSingleUser(@Url String url);
    @FormUrlEncoded
    @POST("/api/register")
    Call<RetrofitRegisterUser> registerUser(@Field("email") String url, @Field("password") String password);
}
