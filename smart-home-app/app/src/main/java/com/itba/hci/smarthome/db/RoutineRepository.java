package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.Routine;
import com.itba.hci.smarthome.service.RoutineService;
import com.itba.hci.smarthome.service.payload.RoutinesResponse;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RoutineRepository {
    private RoutineService routineService;
    MutableLiveData<List<Routine>> routinesLiveData = new MutableLiveData<>();

    public RoutineRepository(RoutineService routineService) {
        this.routineService = routineService;
    }

    public LiveData<List<Routine>> getAllRoutines(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                routineService.getAllRoutines().enqueue(new Callback<RoutinesResponse>() {
                    @Override
                    public void onResponse(Call<RoutinesResponse> call, Response<RoutinesResponse> response) {
                        if(response.body() != null){
                            routinesLiveData.postValue(response.body().getRoutines());
                        }
                    }

                    @Override
                    public void onFailure(Call<RoutinesResponse> call, Throwable t) {
                    }
                });
            }
        });
        return routinesLiveData;
    }
}
