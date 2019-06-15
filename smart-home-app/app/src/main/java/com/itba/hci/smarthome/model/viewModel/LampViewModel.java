package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.LampActionsRepository;

import java.util.ArrayList;
import java.util.List;

public class LampViewModel extends SmartHomeViewModel{
    private LampActionsRepository lampActionsRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(lampActionsRepository);
    }

    public LiveData<Boolean> turnOnLamp(String deviceId){
        return lampActionsRepository.turnOnLamp(deviceId);
    }

    public LiveData<Boolean> turnOffLamp(String deviceId){
        return lampActionsRepository.turnOffLamp(deviceId);
    }

    public LiveData<String> setColorLamp(String deviceId, String color){
        List<String> params = new ArrayList<>();
        params.add(color);

        return lampActionsRepository.setColorLamp(deviceId, params);
    }

    public LiveData<Integer> setBrightnessLamp(String deviceId, Integer brightness){
        List<Integer> params = new ArrayList<>();
        params.add(brightness);

        return lampActionsRepository.setBrightnessLamp(deviceId, params);
    }
}
