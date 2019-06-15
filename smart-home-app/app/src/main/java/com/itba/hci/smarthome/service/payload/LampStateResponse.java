package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.LampState;

public class LampStateResponse {
    private LampState result;

    public LampStateResponse(){

    }

    public LampStateResponse(LampState result) {
        this.result = result;
    }

    public LampState getResult() {
        return result;
    }

    public void setResult(LampState result) {
        this.result = result;
    }
}
