package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.RoutineRepository;

import javax.inject.Inject;

public class DeleteRoutineViewModel extends SmartHomeViewModel{
    @Inject
    RoutineRepository routineRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<Boolean> deleteRoutine(String routineId){
        return routineRepository.deleteRoutine(routineId);
    }

}