package com.itba.hci.smarthome.model.entities;

public class DoorState {
    private String status;
    private String lock;

    public DoorState(){

    }

    public DoorState(String status, String lock) {
        this.status = status;
        this.lock = lock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
