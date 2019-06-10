package com.itba.hci.smarthome.db;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;

import com.itba.hci.smarthome.model.request.LoginRequest;
import com.itba.hci.smarthome.service.LoginService;
import com.itba.hci.smarthome.service.payload.UserResponse;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class LoginRepository {
    private LoginService loginService;
    private MutableLiveData<UserResponse> isLogged = new MutableLiveData<>();
    private Context context;

    public LoginRepository(LoginService loginService, Context context) {
        this.loginService = loginService;
        this.context = context;
    }

    public LiveData<UserResponse> login(final LoginRequest loginRequest) {
        isLogged = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                loginService.login(loginRequest).enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null) {
                            isLogged.postValue(response.body());
                            SharedPreferences pref = context.getSharedPreferences("Pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("isCliente", response.body().getRol().equals("CLIENTE"));
                            editor.commit();
                        } else {
                            isLogged.postValue(new UserResponse(false, "", null, ""));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        isLogged.postValue(new UserResponse(false, "", null, ""));
                    }
                });
            }
        });
        return isLogged;
    }
}

