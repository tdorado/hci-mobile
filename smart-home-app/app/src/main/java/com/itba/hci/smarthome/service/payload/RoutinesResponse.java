package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.Routine;

import java.util.List;

public class RoutinesResponse {
    private List<Routine> routines;

    public RoutinesResponse(){

    }

    public RoutinesResponse(List<Routine> routines) {
        this.routines = routines;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
}
