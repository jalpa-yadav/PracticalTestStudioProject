package com.practicaltest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapters.RecyclerAdapterRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pojo.RetrofitSingleUser;
import com.pojo.RetrofitUserList;
import com.retrofit_calls.ApiClient;
import com.retrofit_calls.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitServiceCallActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RetrofitUserList.Datum> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_service_call);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //start add user screen
                startActivity(new Intent(RetrofitServiceCallActivity.this,AddUserActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerAdapterRetrofit(userList);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        getUserList();
        getSingleUser();
    }

    private void getUserList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<RetrofitUserList> call = apiService.getUserList("2");
        call.enqueue(new Callback<RetrofitUserList>() {
            @Override
            public void onResponse(Call<RetrofitUserList> call, Response<RetrofitUserList> response) {
                int statusCode = response.code();
                Log.e("SUCCESS", response.toString());
                if (response.isSuccessful()) {
                    RetrofitUserList user = response.body();
                    userList.addAll(user.getList());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RetrofitUserList> call, Throwable t) {
                // Log error here since request failed
                Log.e("ERR", t.toString());
            }
        });
    }

    private void getSingleUser() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RetrofitSingleUser> call = apiService.getSingleUser("/api/users/2");
        call.enqueue(new Callback<RetrofitSingleUser>() {
            @Override
            public void onResponse(Call<RetrofitSingleUser> call, Response<RetrofitSingleUser> response) {
                int statusCode = response.code();
                Log.e("SUCCESS", response.toString());
                if (response.isSuccessful()) {
                    RetrofitSingleUser user = response.body();
                    Log.e("Single Object", user.toString());
                }
            }

            @Override
            public void onFailure(Call<RetrofitSingleUser> call, Throwable t) {
                // Log error here since request failed
                Log.e("ERR", t.toString());
            }
        });
    }

}
