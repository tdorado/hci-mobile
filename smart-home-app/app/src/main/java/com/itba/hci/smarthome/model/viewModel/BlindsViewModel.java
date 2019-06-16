package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.BlindsActionsRepository;
import com.itba.hci.smarthome.model.entities.BlindsState;

import javax.inject.Inject;

public class BlindsViewModel extends SmartHomeViewModel{
    @Inject
    BlindsActionsRepository blindsActionsRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<BlindsState> getBlindsState(String deviceId){
        return blindsActionsRepository.getBlindsState(deviceId);
    }

    public LiveData<Boolean> openBlinds(String deviceId){
        return blindsActionsRepository.openBlinds(deviceId);
    }

    public LiveData<Boolean> closeBlinds(String deviceId){
        return blindsActionsRepository.closeBlinds(deviceId);
    }
}
