package com.itba.hci.smarthome.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.bluetooth.BluetoothClass;
import android.content.Context;

import com.itba.hci.smarthome.service.DeviceService;
import com.itba.hci.smarthome.model.request.DeviceRequest;
import com.itba.hci.smarthome.service.payload.DeviceResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.function.Function;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DeviceRepository {
    private DeviceService deviceService;
    private MutableLiveData<List<DeviceResponse>> devicesLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> deviceIsCreated;
    private Set<DeviceResponse> latestDevices = new HashSet<>();

    public DeviceRepository(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public LiveData<List<DeviceResponse>> getAllDevices(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                deviceService.getAllDevices().enqueue(new Callback<List<DeviceResponse>>() {
                    @Override
                    public void onResponse(Call<List<DeviceResponse>> call, Response<List<DeviceResponse>> response) {
                        if(response.body() != null) {
                            devicesLiveData.postValue(response.body());
                            latestDevices.addAll(response.body());
                        }
                        else{
                            devicesLiveData.postValue(Arrays.asList(new DeviceResponse(true)));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DeviceResponse>> call, Throwable t) {
                        devicesLiveData.postValue(Arrays.asList(new DeviceResponse(true)));
                    }
                });
            }
        });
        return devicesLiveData;
    }

    //REMOVER OBSERVER CADA VEZ QUE AGREGO DISPOSITIVO(BUSCAR PORQUE ES JODIDO)
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
                            latestDevices.add(response.body());
                            devicesLiveData.postValue(new ArrayList<>(latestDevices));
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
}
