package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.request.EmptyStringList;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.model.entities.BlindsState;
import com.itba.hci.smarthome.service.payload.BlindsStateResponse;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlindsActionsRepository {
    private DeviceService deviceService;
    private MutableLiveData<BlindsState> blindsStateLiveData;
    private MutableLiveData<Boolean> blindsOpened;
    private MutableLiveData<Boolean> blindsClosed;

    public BlindsActionsRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<BlindsState> getBlindsState(final String deviceId){
        blindsStateLiveData = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getBlindsState(deviceId, EmptyStringList.INSTANCE).enqueue(new Callback<BlindsStateResponse>() {
                    @Override
                    public void onResponse(Call<BlindsStateResponse> call, Response<BlindsStateResponse> response) {
                        if(response.body() != null){
                            blindsStateLiveData.postValue(response.body().getResult());
                        }
                        else{
                            blindsStateLiveData.postValue(new BlindsState());
                        }
                    }

                    @Override
                    public void onFailure(Call<BlindsStateResponse> call, Throwable t) {
                        blindsStateLiveData.postValue(new BlindsState());
                    }
                });
            }
        });
        return blindsStateLiveData;
    }

    public LiveData<Boolean> openBlinds(final String deviceId){
        blindsOpened = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "open", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            blindsOpened.postValue(response.body().getResult());
                        }
                        else{
                            blindsOpened.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        blindsOpened.postValue(false);

                    }
                });
            }
        });
        return blindsOpened;
    }

    public LiveData<Boolean> closeBlinds(final String deviceId){
        blindsClosed = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "close", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            blindsClosed.postValue(response.body().getResult());
                        }
                        else{
                            blindsClosed.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        blindsClosed.postValue(false);

                    }
                });
            }
        });
        return blindsClosed;
    }

}
