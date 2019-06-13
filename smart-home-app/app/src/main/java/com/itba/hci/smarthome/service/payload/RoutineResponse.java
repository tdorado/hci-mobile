package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.Routine;

public class RoutineResponse {
    private Routine routine;

    public RoutineResponse(){

    }

    public RoutineResponse(Routine routine) {
        this.routine = routine;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
