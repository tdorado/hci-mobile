package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.DeviceRepository;
import com.itba.hci.smarthome.model.entities.Device;

import java.util.List;

import javax.inject.Inject;

public class DeviceViewModel extends SmartHomeViewModel {
    @Inject
    DeviceRepository deviceRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<List<Device>> getAllDevices(){
        return deviceRepository.getAllDevices();
    }

}
