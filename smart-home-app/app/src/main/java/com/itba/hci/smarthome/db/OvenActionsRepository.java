package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.OvenState;
import com.itba.hci.smarthome.model.request.EmptyStringList;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.IntegerResultResponse;
import com.itba.hci.smarthome.service.payload.OvenStateResponse;
import com.itba.hci.smarthome.service.payload.StringResultResponse;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OvenActionsRepository {
    private DeviceService deviceService;
    private MutableLiveData<OvenState> ovenStateLiveData;
    private MutableLiveData<Boolean> ovenTurnedOn;
    private MutableLiveData<Boolean> ovenTurnedOff;
    private MutableLiveData<Integer> ovenChangesTemperature;
    private MutableLiveData<String> ovenChangesHeat;
    private MutableLiveData<String> ovenChangesGrill;
    private MutableLiveData<String> ovenChangesConvection;


    public OvenActionsRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<OvenState> getOvenState(final String deviceId){
        ovenStateLiveData = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getOvenState(deviceId, EmptyStringList.INSTANCE).enqueue(new Callback<OvenStateResponse>() {
                    @Override
                    public void onResponse(Call<OvenStateResponse> call, Response<OvenStateResponse> response) {
                        if(response.body() != null){
                            ovenStateLiveData.postValue(response.body().getResult());
                        }
                        else{
                            ovenStateLiveData.postValue(new OvenState());
                        }
                    }

                    @Override
                    public void onFailure(Call<OvenStateResponse> call, Throwable t) {
                        ovenStateLiveData.postValue(new OvenState());
                    }
                });
            }
        });

        return ovenStateLiveData;
    }

    public LiveData<Boolean> turnOnOven(final String deviceId){
        ovenTurnedOn = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "turnOn", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            ovenTurnedOn.postValue(response.body().getResult());
                        }
                        else{
                            ovenTurnedOn.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        ovenTurnedOn.postValue(false);
                    }
                });
            }
        });

        return ovenTurnedOn;
    }

    public LiveData<Boolean> turnOffOven(final String deviceId){
        ovenTurnedOff = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "turnOff", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            ovenTurnedOff.postValue(response.body().getResult());
                        }
                        else{
                            ovenTurnedOff.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        ovenTurnedOff.postValue(false);
                    }
                });
            }
        });

        return ovenTurnedOff;
    }

    public LiveData<Integer> setTemperatureOven(final String deviceId, final List<Integer> params){
        ovenChangesTemperature = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithInteger(deviceId, "setTemperature", params).enqueue(new Callback<IntegerResultResponse>() {
                    @Override
                    public void onResponse(Call<IntegerResultResponse> call, Response<IntegerResultResponse> response) {
                        if(response.body() != null){
                            ovenChangesTemperature.postValue(response.body().getResult());
                        }
                        else{
                            ovenChangesTemperature.postValue(-1);
                        }
                    }

                    @Override
                    public void onFailure(Call<IntegerResultResponse> call, Throwable t) {
                        ovenChangesTemperature.postValue(-1);
                    }
                });
            }
        });

        return ovenChangesTemperature;
    }

    public LiveData<String> setHeatOven(final String deviceId, final List<String> params){
        ovenChangesHeat = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setHeat", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            ovenChangesHeat.postValue(response.body().getResult());
                        }
                        else{
                            ovenChangesHeat.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        ovenChangesHeat.postValue("null");
                    }
                });
            }
        });

        return ovenChangesHeat;
    }

    public LiveData<String> setGrillOven(final String deviceId, final List<String> params){
        ovenChangesGrill = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setGrill", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            ovenChangesGrill.postValue(response.body().getResult());
                        }
                        else{
                            ovenChangesGrill.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        ovenChangesGrill.postValue("null");
                    }
                });
            }
        });

        return ovenChangesGrill;
    }

    public LiveData<String> setConvectionOven(final String deviceId, final List<String> params){
        ovenChangesConvection = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithString(deviceId, "setConvection", params).enqueue(new Callback<StringResultResponse>() {
                    @Override
                    public void onResponse(Call<StringResultResponse> call, Response<StringResultResponse> response) {
                        if(response.body() != null){
                            ovenChangesConvection.postValue(response.body().getResult());
                        }
                        else{
                            ovenChangesConvection.postValue("null");
                        }
                    }

                    @Override
                    public void onFailure(Call<StringResultResponse> call, Throwable t) {
                        ovenChangesConvection.postValue("null");
                    }
                });
            }
        });

        return ovenChangesConvection;
    }
}
