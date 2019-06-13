package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.DeviceRepository;
import com.itba.hci.smarthome.model.request.DeviceRequest;

import javax.inject.Inject;

public class NewDeviceViewModel extends SmartHomeViewModel {
    @Inject
    DeviceRepository deviceRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<Boolean> createDevice(DeviceRequest deviceRequest){
        return deviceRepository.createDevice(deviceRequest);
    }

    public LiveData<Boolean> getDeviceIsCreatedLiveData(){
        return deviceRepository.getDeviceIsCreatedLiveData();
    }
}
