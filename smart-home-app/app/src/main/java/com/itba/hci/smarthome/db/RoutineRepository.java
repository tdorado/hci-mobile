package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.service.RoutineService;
import com.itba.hci.smarthome.service.payload.RoutineResponse;

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
    MutableLiveData<List<RoutineResponse>> routinesLiveData = new MutableLiveData<>();

    public RoutineRepository(RoutineService routineService) {
        this.routineService = routineService;
    }

    public LiveData<List<RoutineResponse>> getAllRoutines(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                routineService.getAllRoutines().enqueue(new Callback<List<RoutineResponse>>() {
                    @Override
                    public void onResponse(Call<List<RoutineResponse>> call, Response<List<RoutineResponse>> response) {
                        if(response.body() != null){
                            routinesLiveData.postValue(response.body());
                        }
                        else{
                            routinesLiveData.postValue(Arrays.asList(new RoutineResponse(true)));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RoutineResponse>> call, Throwable t) {
                        routinesLiveData.postValue(Arrays.asList(new RoutineResponse(true)));
                    }
                });
            }
        });
        return routinesLiveData;
    }
}
