package com.itba.hci.smarthome.service.payload;

public class DoorStateResponse {
    private String status;
    private String lock;

    public DoorStateResponse(){

    }

    public DoorStateResponse(String status, String lock) {
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
