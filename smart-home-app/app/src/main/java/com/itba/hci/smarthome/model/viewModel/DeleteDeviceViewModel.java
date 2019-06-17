package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.DeviceRepository;

import javax.inject.Inject;

public class DeleteDeviceViewModel extends SmartHomeViewModel{
    @Inject
    DeviceRepository deviceRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<Boolean> deleteDevice(String deviceId){
        return deviceRepository.deleteDevice(deviceId);
    }

}
