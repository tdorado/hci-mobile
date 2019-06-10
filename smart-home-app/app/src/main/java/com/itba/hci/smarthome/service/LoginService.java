package com.itba.hci.smarthome.service;

import com.itba.hci.smarthome.model.request.LoginRequest;
import com.itba.hci.smarthome.service.payload.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/login")
    Call<UserResponse> login(@Body LoginRequest body);
}