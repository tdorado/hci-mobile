package com.itba.hci.smarthome.service;

import com.itba.hci.smarthome.model.request.EmptyRequest;
import com.itba.hci.smarthome.service.payload.RoutineResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RoutineService {
    @GET("routines")
    Call<List<RoutineResponse>> getAllRoutines();

    @PUT("routines/{routineId}/execute")
    Call<Boolean> executeRoutine(@Path("routineId") String routineId, @Body EmptyRequest emptyRequest);
}
