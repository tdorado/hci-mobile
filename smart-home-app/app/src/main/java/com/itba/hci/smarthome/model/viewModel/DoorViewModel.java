package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.DoorActionsRepository;
import com.itba.hci.smarthome.model.entities.DoorState;

import javax.inject.Inject;

public class DoorViewModel extends SmartHomeViewModel{
    @Inject
    DoorActionsRepository doorActionsRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<DoorState> getDoorState(String deviceId){
        return doorActionsRepository.getDoorState(deviceId);
    }

    public LiveData<Boolean> openDoor(String deviceId){
        return doorActionsRepository.openDoor(deviceId);
    }

    public LiveData<Boolean> closeDoor(String deviceId){
        return doorActionsRepository.closeDoor(deviceId);
    }

    public LiveData<Boolean> lockDoor(String deviceId){
        return doorActionsRepository.lockDoor(deviceId);
    }

    public LiveData<Boolean> unlockDoor(String deviceId){
        return doorActionsRepository.unlockDoor(deviceId);
    }
}
