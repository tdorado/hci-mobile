package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.OvenActionsRepository;
import com.itba.hci.smarthome.model.entities.OvenState;

import java.util.ArrayList;
import java.util.List;

public class OvenViewModel extends SmartHomeViewModel{
    private OvenActionsRepository ovenActionsRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(ovenActionsRepository);
    }

    public LiveData<OvenState> getOvenState(String deviceId){
        return ovenActionsRepository.getOvenState(deviceId);
    }

    public LiveData<Boolean> turnOnOven(String deviceId){
        return ovenActionsRepository.turnOnOven(deviceId);
    }

    public LiveData<Boolean> turnOffOven(String deviceId){
        return ovenActionsRepository.turnOffOven(deviceId);
    }

    public LiveData<Integer> setTemperatureOven(String deviceId, Integer temperature){
        List<Integer> params = new ArrayList<>();
        params.add(temperature);

        return ovenActionsRepository.setTemperatureOven(deviceId, params);
    }

    public LiveData<String> setHeatOven(String deviceId, String heat){
        List<String> params = new ArrayList<>();
        params.add(heat);

        return ovenActionsRepository.setHeatOven(deviceId, params);
    }

    public LiveData<String> setGrillOven(String deviceId, String grill){
        List<String> params = new ArrayList<>();
        params.add(grill);

        return ovenActionsRepository.setGrillOven(deviceId, params);
    }

    public LiveData<String> setConvectionOven(String deviceId, String convection){
        List<String> params = new ArrayList<>();
        params.add(convection);

        return ovenActionsRepository.setConvectionOven(deviceId, params);
    }
}
