package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.AlarmState;

public class AlarmStateResponse {
    private AlarmState result;

    public AlarmStateResponse(){

    }

    public AlarmStateResponse(AlarmState result) {
        this.result = result;
    }

    public AlarmState getResult() {
        return result;
    }

    public void setResult(AlarmState result) {
        this.result = result;
    }
}
