package com.practicaltest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pojo.RetrofitRegisterUser;
import com.retrofit_calls.ApiClient;
import com.retrofit_calls.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate())
                {
//                    TESTING DATA
//                    {
//                        "email": "eve.holt@reqres.in",
//                            "password": "pistol"
//                    }
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<RetrofitRegisterUser> call = apiService.registerUser(etEmail.getText().toString(),etPassword.getText().toString());
                    call.enqueue(new Callback<RetrofitRegisterUser>() {
                        @Override
                        public void onResponse(Call<RetrofitRegisterUser> call, Response<RetrofitRegisterUser> response) {
                            int statusCode = response.code();
                            Log.e("SUCCESS", response.toString());
                            if (response.isSuccessful()) {
                                RetrofitRegisterUser user = response.body();
                                Log.e("Register successful", user.toString());
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<RetrofitRegisterUser> call, Throwable t) {
                            // Log error here since request failed
                            Log.e("ERR", t.toString());
                        }
                    });
                }
            }
        });

    }

    private boolean isValidate() {
        if(TextUtils.isEmpty(etEmail.getText().toString()))
        {
            etEmail.setError("Enter Email");
            etEmail.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(etPassword.getText().toString()))
        {
            etPassword.setError("Enter Password");
            etPassword.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(etConfirmPassword.getText().toString()))
        {
            etConfirmPassword.setError("Enter Confirm Password");
            etConfirmPassword.requestFocus();
            return false;
        }
        if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
        {
            etConfirmPassword.setError("Passwords does not match");
            etConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }
}
