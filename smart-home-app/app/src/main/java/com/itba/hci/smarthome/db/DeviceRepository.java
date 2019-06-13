package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.model.request.DeviceRequest;
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
}
