package com.itba.hci.smarthome.service.payload;

public class AlarmStateResponse {
    private String status;

    public AlarmStateResponse(){

    }

    public AlarmStateResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
