package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.DoorState;
import com.itba.hci.smarthome.model.request.EmptyStringList;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.DoorStateResponse;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorActionsRepository {
    private DeviceService deviceService;
    private MutableLiveData<DoorState> doorStateLiveData;
    private MutableLiveData<Boolean> doorIsOpened;
    private MutableLiveData<Boolean> doorIsClosed;
    private MutableLiveData<Boolean> doorIsUnlocked;
    private MutableLiveData<Boolean> doorIsLocked;

    public DoorActionsRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<DoorState> getDoorState(final String deviceId){
        doorStateLiveData = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getDoorState(deviceId, EmptyStringList.INSTANCE).enqueue(new Callback<DoorStateResponse>() {
                    @Override
                    public void onResponse(Call<DoorStateResponse> call, Response<DoorStateResponse> response) {
                        if(response.body() != null){
                            doorStateLiveData.postValue(response.body().getResult());
                        }
                        else{
                            doorStateLiveData.postValue(new DoorState());
                        }
                    }

                    @Override
                    public void onFailure(Call<DoorStateResponse> call, Throwable t) {
                        doorStateLiveData.postValue(new DoorState());
                    }
                });
            }
        });

        return doorStateLiveData;
    }

    public LiveData<Boolean> openDoor(final String deviceId){
        doorIsOpened = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "open", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            doorIsOpened.postValue(response.body().getResult());
                        }
                        else{
                            doorIsOpened.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        doorIsOpened.postValue(false);
                    }
                });
            }
        });

        return doorIsOpened;
    }

    public LiveData<Boolean> closeDoor(final String deviceId){
        doorIsClosed = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "close", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            doorIsClosed.postValue(response.body().getResult());
                        }
                        else{
                            doorIsClosed.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        doorIsClosed.postValue(false);
                    }
                });
            }
        });

        return doorIsClosed;
    }

    public LiveData<Boolean> lockDoor(final String deviceId){
        doorIsLocked = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "lock", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            doorIsLocked.postValue(response.body().getResult());
                        }
                        else{
                            doorIsLocked.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        doorIsLocked.postValue(false);
                    }
                });
            }
        });

        return doorIsLocked;
    }

    public LiveData<Boolean> unlockDoor(final String deviceId){
        doorIsUnlocked = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.actionWithStringAndBooleanResponse(deviceId, "unlock", EmptyStringList.INSTANCE).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null){
                            doorIsUnlocked.postValue(response.body().getResult());
                        }
                        else{
                            doorIsUnlocked.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        doorIsUnlocked.postValue(false);
                    }
                });
            }
        });

        return doorIsUnlocked;
    }
}
