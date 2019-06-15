package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.AlarmState;
import com.itba.hci.smarthome.model.request.EmptyStringList;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.payload.AlarmStateResponse;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmActionsRepository {
    private DeviceService deviceService;
    private MutableLiveData<AlarmState> alarmStateLiveData;
    private MutableLiveData<Boolean> alarmChangesSecurityCode;
    private MutableLiveData<Boolean> alarmArmsStay;
    private MutableLiveData<Boolean> alarmArmsAway;
    private MutableLiveData<Boolean> alarmDisarms;

    public AlarmActionsRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<AlarmState> getAlarmState(final String deviceId){
        alarmStateLiveData = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getAlarmState(deviceId, EmptyStringList.INSTANCE).enqueue(new Callback<AlarmStateResponse>() {
                    @Override
                    public void onResponse(Call<AlarmStateResponse> call, Response<AlarmStateResponse> response) {
                        if(response.body() != null){
                            alarmStateLiveData.postValue(response.body().getResult());
                        }
                        else{
                            alarmStateLiveData.postValue(new AlarmState());
                        }
                    }

                    @Override
                    public void onFailure(Call<AlarmStateResponse> call, Throwable t) {
                        alarmStateLiveData.postValue(new AlarmState());
                    }
                });
            }
        });

        return alarmStateLiveData;
    }

    public LiveData<Boolean> changeSecurityCodeAlarm(final String deviceId, final List<String> params){
        alarmChangesSecurityCode = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "changeSecurityCode", params).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            alarmChangesSecurityCode.postValue(response.body().getResult());
                        }
                        else{
                            alarmChangesSecurityCode.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        alarmChangesSecurityCode.postValue(false);
                    }
                });
            }
        });

        return alarmChangesSecurityCode;
    }

    public LiveData<Boolean> armsStayAlarm(final String deviceId, final List<String> params){
        alarmArmsStay = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "armStay", params).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            alarmArmsStay.postValue(response.body().getResult());
                        }
                        else{
                            alarmArmsStay.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        alarmArmsStay.postValue(false);
                    }
                });
            }
        });

        return alarmArmsStay;
    }

    public LiveData<Boolean> armsAwayAlarm(final String deviceId, final List<String> params){
        alarmArmsAway = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "armAway", params).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            alarmArmsAway.postValue(response.body().getResult());
                        }
                        else{
                            alarmArmsAway.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        alarmArmsAway.postValue(false);
                    }
                });
            }
        });

        return alarmArmsAway;
    }

    public LiveData<Boolean> disarmsAlarm(final String deviceId, final List<String> params){
        alarmDisarms = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "disarm", params).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            alarmDisarms.postValue(response.body().getResult());
                        }
                        else{
                            alarmDisarms.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        alarmDisarms.postValue(false);
                    }
                });
            }
        });

        return alarmDisarms;
    }
}
