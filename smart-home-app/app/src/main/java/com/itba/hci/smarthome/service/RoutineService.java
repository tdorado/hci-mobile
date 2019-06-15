package com.itba.hci.smarthome.service;

import com.itba.hci.smarthome.model.request.EmptyRequest;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.ExecuteRoutineResponse;
import com.itba.hci.smarthome.service.payload.RoutinesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RoutineService {
    @GET("routines")
    Call<RoutinesResponse> getAllRoutines();

    @PUT("routines/{routineId}/execute")
    Call<ExecuteRoutineResponse> executeRoutine(@Path("routineId") String routineId, @Body EmptyRequest emptyRequest);

    @DELETE("routines/{routineId}")
    Call<BooleanResultResponse> deleteRoutine(@Path("routineId") String routineId);
}
