package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.DeviceRepository;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.request.DeviceRequest;

import javax.inject.Inject;

public class EditDeviceViewModel extends SmartHomeViewModel {
    @Inject
    DeviceRepository deviceRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<Boolean> editDevice(String deviceId, DeviceRequest deviceRequest){
        return deviceRepository.updateDevice(deviceId, deviceRequest);
    }

    public LiveData<Device> getDevice(String deviceId){
        return deviceRepository.getDevice(deviceId);
    }
}
