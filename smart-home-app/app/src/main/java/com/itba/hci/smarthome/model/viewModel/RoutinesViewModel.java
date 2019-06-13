package com.itba.hci.smarthome.model.viewModel;

import android.arch.lifecycle.LiveData;

import com.itba.hci.smarthome.dagger.components.SmartHomeComponents;
import com.itba.hci.smarthome.db.RoutineRepository;
import com.itba.hci.smarthome.model.entities.Routine;

import java.util.List;

import javax.inject.Inject;

public class RoutinesViewModel extends SmartHomeViewModel{
    @Inject
    RoutineRepository routineRepository;

    @Override
    public void initialize(SmartHomeComponents component) {
        component.inject(this);
    }

    public LiveData<List<Routine>> getAllRoutines(){
        return routineRepository.getAllRoutines();
    }

    public LiveData<List<Boolean>> executeRoutine(String routineId){
        return routineRepository.executeRoutine(routineId);
    }
}
