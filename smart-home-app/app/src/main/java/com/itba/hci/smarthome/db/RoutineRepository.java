package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.Routine;
import com.itba.hci.smarthome.model.request.EmptyRequest;
import com.itba.hci.smarthome.service.RoutineService;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.ExecuteRoutineResponse;
import com.itba.hci.smarthome.service.payload.RoutinesResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RoutineRepository {
    private RoutineService routineService;
    private MutableLiveData<List<Routine>> routinesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Boolean>> routinesExecuted;
    private MutableLiveData<Boolean> routineDeleted;

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
                        else{
                            routinesLiveData.postValue(new ArrayList<Routine>());
                        }
                    }

                    @Override
                    public void onFailure(Call<RoutinesResponse> call, Throwable t) {
                        routinesLiveData.postValue(new ArrayList<Routine>());
                    }
                });
            }
        });
        return routinesLiveData;
    }

    public LiveData<List<Boolean>> executeRoutine(final String routineId){
        routinesExecuted = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                routineService.executeRoutine(routineId, EmptyRequest.INSTANCE).enqueue(new Callback<ExecuteRoutineResponse>() {
                    @Override
                    public void onResponse(Call<ExecuteRoutineResponse> call, Response<ExecuteRoutineResponse> response) {
                        if(response.body() != null){
                            routinesExecuted.postValue(response.body().getResult());
                        }
                        else{
                            routinesExecuted.postValue(new ArrayList<Boolean>());
                        }
                    }

                    @Override
                    public void onFailure(Call<ExecuteRoutineResponse> call, Throwable t) {
                        routinesExecuted.postValue(new ArrayList<Boolean>());
                    }
                });
            }
        });
        return routinesExecuted;
    }

    public LiveData<Boolean> deleteRoutine(final String routineId){
        routineDeleted = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                routineService.deleteRoutine(routineId).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null && response.body().getResult()){
                            routineDeleted.postValue(true);
                        }
                        else{
                            routineDeleted.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        routineDeleted.postValue(false);
                    }
                });

            }
        });

        return routineDeleted;
    }
}
