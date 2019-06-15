package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.AcActionsRepository;
import com.itba.hci.smarthome.model.entities.AcState;

import java.util.ArrayList;
import java.util.List;

public class AcViewModel extends SmartHomeViewModel{
    private AcActionsRepository acActionsRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(acActionsRepository);
    }

    public LiveData<AcState> getAcState(String deviceId){
        return acActionsRepository.getAcState(deviceId);
    }

    public LiveData<Boolean> turnOnAc(String deviceId){
        return acActionsRepository.turnOnAc(deviceId);
    }

    public LiveData<Boolean> turnOffAc(String deviceId){
        return acActionsRepository.turnOffAc(deviceId);
    }

    public LiveData<Integer> setTemperatureAc(String deviceId, Integer temperature){
        List<Integer> params = new ArrayList<>();
        params.add(temperature);
        return acActionsRepository.setTemperatureAc(deviceId, params);
    }

    public LiveData<String> setModeAc(String deviceId, String mode){
        List<String> params = new ArrayList<>();
        params.add(mode);
        return acActionsRepository.setModeAc(deviceId, params);
    }

    public LiveData<String> setVerticalSwingAc(String deviceId, String verticalSwing){
        List<String> params = new ArrayList<>();
        params.add(verticalSwing);
        return acActionsRepository.setVerticalSwingAc(deviceId, params);
    }

    public LiveData<String> setHorizontalSwingAc(String deviceId, String horizontalSwing){
        List<String> params = new ArrayList<>();
        params.add(horizontalSwing);
        return acActionsRepository.setHorizontalSwingAc(deviceId, params);
    }

    public LiveData<String> setFanSpeedAc(String deviceId, String fanSpeed){
        List<String> params = new ArrayList<>();
        params.add(fanSpeed);
        return acActionsRepository.setFanSpeedAc(deviceId, params);
    }
}
