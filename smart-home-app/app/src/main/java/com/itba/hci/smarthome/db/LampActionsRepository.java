package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.LampState;
import com.itba.hci.smarthome.model.request.EmptyStringList;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.IntegerResultResponse;
import com.itba.hci.smarthome.service.payload.LampStateResponse;
import com.itba.hci.smarthome.service.payload.StringResultResponse;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LampActionsRepository {
    private DeviceService deviceService;
    private MutableLiveData<LampState> lampStateLiveData;
    private MutableLiveData<Boolean> lampIsTurnedOn;
    private MutableLiveData<Boolean> lampIsTurnedOff;
    private MutableLiveData<String> lampChangesColor;
    private MutableLiveData<Integer> lampChangesBrightness;

    public LampActionsRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<LampState> getLampState(final String deviceId){
        lampStateLiveData = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getLampState(deviceId, EmptyStringList.INSTANCE).enqueue(new Callback<LampStateResponse>() {
                    @Override
                    public void onResponse(Call<LampStateResponse> call, Response<LampStateResponse> response) {
                        if(response.body() != null){
                            lampStateLiveData.postValue(response.body().getResult());
                        }
                        else{
                            lampStateLiveData.postValue(new LampState());
                        }
                    }

                    @Override
                    public void onFailure(Call<LampStateResponse> call, Throwable t) {
                        lampStateLiveData.postValue(new LampState());
                    }
                });
            }
        });
        return lampStateLiveData;
    }

    public LiveData<Boolean> turnOnLamp(final String deviceId){
        lampIsTurnedOn = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "turnOn", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            lampIsTurnedOn.postValue(response.body().getResult());
                        }
                        else{
                            lampIsTurnedOn.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        lampIsTurnedOn.postValue(false);
                    }
                });
            }
        });

        return lampIsTurnedOn;
    }

    public LiveData<Boolean> turnOffLamp(final String deviceId){
        lampIsTurnedOff = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "turnOff", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            lampIsTurnedOff.postValue(response.body().getResult());
                        }
                        else{
                            lampIsTurnedOff.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        lampIsTurnedOff.postValue(false);
                    }
                });
            }
        });
        return lampIsTurnedOff;
    }

    public LiveData<String> setColorLamp(final String deviceId, final List<String> params){
        lampChangesColor = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setColor", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            lampChangesColor.postValue(response.body().getResult());
                        }
                        else{
                            lampChangesColor.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        lampChangesColor.postValue("null");
                    }
                });
            }
        });
        return lampChangesColor;
    }

    public LiveData<Integer> setBrightnessLamp(final String deviceId, final List<Integer> params){
        lampChangesBrightness = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithInteger(deviceId, "setBrightness", params).enqueue(new Callback<IntegerResultResponse>() {
                    @Override
                    public void onResponse(Call<IntegerResultResponse> call, Response<IntegerResultResponse> response) {
                        if(response.body() != null){
                            lampChangesBrightness.postValue(response.body().getResult());
                        }
                        else{
                            lampChangesBrightness.postValue(-1);
                        }
                    }

                    @Override
                    public void onFailure(Call<IntegerResultResponse> call, Throwable t) {
                        lampChangesBrightness.postValue(-1);
                    }
                });
            }
        });
        return lampChangesBrightness;
    }
}
