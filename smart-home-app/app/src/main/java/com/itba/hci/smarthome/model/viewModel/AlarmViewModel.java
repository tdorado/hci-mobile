package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.AlarmActionsRepository;
import com.itba.hci.smarthome.model.entities.AlarmState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlarmViewModel extends SmartHomeViewModel{
    @Inject
    AlarmActionsRepository alarmActionsRepository;


    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<AlarmState> getAlarmState(String deviceId){
        return alarmActionsRepository.getAlarmState(deviceId);
    }

    public LiveData<Boolean> changeSecurityCodeAlarm(String deviceId, String oldSecurityCode, String newSecurityCode){
        List<String> params = new ArrayList<>();
        params.add(oldSecurityCode);
        params.add(newSecurityCode);

        return alarmActionsRepository.changeSecurityCodeAlarm(deviceId, params);
    }

    public LiveData<Boolean> armStayAlarm(String deviceId, String securityCode){
        List<String> params = new ArrayList<>();
        params.add(securityCode);

        return alarmActionsRepository.armsStayAlarm(deviceId, params);
    }

    public LiveData<Boolean> armAwayAlarm(String deviceId, String securityCode){
        List<String> params = new ArrayList<>();
        params.add(securityCode);

        return alarmActionsRepository.armsAwayAlarm(deviceId, params);
    }

    public LiveData<Boolean> disarmAlarm(String deviceId, String securityCode){
        List<String> params = new ArrayList<>();
        params.add(securityCode);

        return alarmActionsRepository.disarmsAlarm(deviceId, params);
    }
}
