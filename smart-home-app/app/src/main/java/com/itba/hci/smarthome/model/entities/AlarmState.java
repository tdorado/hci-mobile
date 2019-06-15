package com.itba.hci.smarthome.model.entities;

public class AlarmState {
    private String status;

    public AlarmState(){

    }

    public AlarmState(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
