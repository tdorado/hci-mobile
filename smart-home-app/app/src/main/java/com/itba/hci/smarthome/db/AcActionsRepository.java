package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.AcState;
import com.itba.hci.smarthome.model.request.EmptyStringList;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.payload.AcStateResponse;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.IntegerResultResponse;
import com.itba.hci.smarthome.service.payload.StringResultResponse;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcActionsRepository {
    private DeviceService deviceService;
    private MutableLiveData<AcState> acStateLiveData;
    private MutableLiveData<Boolean> acTurnedOn;
    private MutableLiveData<Boolean> acTurnedOff;
    private MutableLiveData<Integer> acChangesTemperature;
    private MutableLiveData<String> acChangesMode;
    private MutableLiveData<String> acChangesVerticalSwing;
    private MutableLiveData<String> acChangesHorizontalSwing;
    private MutableLiveData<String> acChangesFanSpeed;


    public AcActionsRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<AcState> getAcState(final String deviceId){
        acStateLiveData = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getAcState(deviceId, EmptyStringList.INSTANCE).enqueue(new Callback<AcStateResponse>() {
                    @Override
                    public void onResponse(Call<AcStateResponse> call, Response<AcStateResponse> response) {
                        if(response.body() != null){
                            acStateLiveData.postValue(response.body().getResult());
                        }
                        else{
                            acStateLiveData.postValue(new AcState());
                        }
                    }

                    @Override
                    public void onFailure(Call<AcStateResponse> call, Throwable t) {
                        acStateLiveData.postValue(new AcState());
                    }
                });
            }
        });

        return acStateLiveData;
    }

    public LiveData<Boolean> turnOnAc(final String deviceId){
        acTurnedOn = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "turnOn", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            acTurnedOn.postValue(response.body().getResult());
                        }
                        else{
                            acTurnedOn.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        acTurnedOn.postValue(false);
                    }
                });
            }
        });
        return acTurnedOn;
    }

    public LiveData<Boolean> turnOffAc(final String deviceId){
        acTurnedOff = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "turnOff", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            acTurnedOff.postValue(response.body().getResult());
                        }
                        else{
                            acTurnedOff.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        acTurnedOff.postValue(false);
                    }
                });
            }
        });
        return acTurnedOff;
    }

    public LiveData<Integer> setTemperatureAc(final String deviceId, final List<Integer> params){
        acChangesTemperature = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithInteger(deviceId, "setTemperature", params).enqueue(new Callback<IntegerResultResponse>() {
                    @Override
                    public void onResponse(Call<IntegerResultResponse> call, Response<IntegerResultResponse> response) {
                        if(response.body() != null){
                            acChangesTemperature.postValue(response.body().getResult());
                        }
                        else{
                            acChangesTemperature.postValue(-1);
                        }
                    }

                    @Override
                    public void onFailure(Call<IntegerResultResponse> call, Throwable t) {
                        acChangesTemperature.postValue(-1);
                    }
                });
            }
        });

        return acChangesTemperature;
    }

    public LiveData<String> setModeAc(final String deviceId, final List<String> params){
        acChangesMode = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setMode", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            acChangesMode.postValue(response.body().getResult());
                        }
                        else{
                            acChangesMode.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        acChangesMode.postValue("null");
                    }
                });
            }
        });

        return acChangesMode;
    }

    public LiveData<String> setVerticalSwingAc(final String deviceId, final List<String> params){
        acChangesVerticalSwing = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setVerticalSwing", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            acChangesVerticalSwing.postValue(response.body().getResult());
                        }
                        else{
                            acChangesVerticalSwing.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        acChangesVerticalSwing.postValue("null");
                    }
                });
            }
        });

        return acChangesVerticalSwing;
    }

    public LiveData<String> setHorizontalSwingAc(final String deviceId, final List<String> params){
        acChangesHorizontalSwing = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setHorizontalSwing", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            acChangesHorizontalSwing.postValue(response.body().getResult());
                        }
                        else{
                            acChangesHorizontalSwing.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        acChangesHorizontalSwing.postValue("null");
                    }
                });
            }
        });

        return acChangesHorizontalSwing;
    }

    public LiveData<String> setFanSpeed(final String deviceId, final List<String> params){
        acChangesFanSpeed = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setFanSpeed", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            acChangesFanSpeed.postValue(response.body().getResult());
                        }
                        else{
                            acChangesFanSpeed.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        acChangesFanSpeed.postValue("null");
                    }
                });
            }
        });

        return acChangesFanSpeed;
    }
}
