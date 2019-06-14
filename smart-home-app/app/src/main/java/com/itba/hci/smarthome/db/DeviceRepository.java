package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.service.payload.BooleanResultResponse;
import com.itba.hci.smarthome.service.payload.DeviceResponse;
import com.itba.hci.smarthome.service.payload.DevicesResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DeviceRepository {
    private DeviceService deviceService;
    private MutableLiveData<List<Device>> devicesLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> deviceIsCreated;
    private MutableLiveData<Boolean> deviceIsUpdated;
    private MutableLiveData<Boolean> deviceIsDeleted;
    private MutableLiveData<Device> deviceGotten;

    public DeviceRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<List<Device>> getAllDevices(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getAllDevices().enqueue(new Callback<DevicesResponse>() {
                    @Override
                    public void onResponse(Call<DevicesResponse> call, Response<DevicesResponse> response) {
                        if(response.body() != null) {
                            devicesLiveData.postValue(response.body().getDevices());
                        }
                        else{
                            devicesLiveData.postValue(new ArrayList<Device>());
                        }
                    }

                    @Override
                    public void onFailure(Call<DevicesResponse> call, Throwable t) {
                        devicesLiveData.postValue(new ArrayList<Device>());
                    }
                });
            }
        });
        return devicesLiveData;
    }

    public LiveData<Boolean> createDevice(final DeviceRequest deviceRequest){
        deviceIsCreated = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.createDevice(deviceRequest).enqueue(new Callback<DeviceResponse>() {
                    @Override
                    public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {
                        if(response.body() != null){
                            deviceIsCreated.postValue(true);
                        }
                        else{
                            deviceIsCreated.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<DeviceResponse> call, Throwable t) {
                        deviceIsCreated.postValue(false);
                    }
                });

            }
        });

        return deviceIsCreated;
    }

    public LiveData<Boolean> getDeviceIsCreatedLiveData() {
        return deviceIsCreated;
    }

    public LiveData<Boolean> updateDevice(final String deviceId, final DeviceRequest deviceRequest){
        deviceIsUpdated = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.updateDevice(deviceId, deviceRequest).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null && response.body().getResult()){
                            deviceIsUpdated.postValue(true);
                        }
                        else{
                            deviceIsUpdated.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        deviceIsUpdated.postValue(false);
                    }
                });

            }
        });

        return deviceIsUpdated;
    }

    public LiveData<Boolean> deleteDevice(final String deviceId){
        deviceIsDeleted = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.deleteDevice(deviceId).enqueue(new Callback<BooleanResultResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResultResponse> call, Response<BooleanResultResponse> response) {
                        if(response.body() != null && response.body().getResult()){
                            deviceIsDeleted.postValue(true);
                        }
                        else{
                            deviceIsDeleted.postValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResultResponse> call, Throwable t) {
                        deviceIsDeleted.postValue(false);
                    }
                });

            }
        });

        return deviceIsDeleted;
    }

    public LiveData<Device> getDevice(final String deviceId){
        deviceGotten = new MutableLiveData<>();

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getDevice(deviceId).enqueue(new Callback<DeviceResponse>() {
                    @Override
                    public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {
                        if(response.body() != null){
                            deviceGotten.postValue(response.body().getDevice());
                        }
                        else{
                            deviceGotten.postValue(new Device());
                        }
                    }

                    @Override
                    public void onFailure(Call<DeviceResponse> call, Throwable t) {
                        deviceGotten.postValue(new Device());
                    }
                });
            }
        });
        return deviceGotten;
    }

}
