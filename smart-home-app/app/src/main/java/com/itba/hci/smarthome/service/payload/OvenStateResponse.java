package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.OvenState;

public class OvenStateResponse {
    private OvenState result;

    public OvenStateResponse(){

    }

    public OvenStateResponse(OvenState result) {
        this.result = result;
    }

    public OvenState getResult() {
        return result;
    }

    public void setResult(OvenState result) {
        this.result = result;
    }
}
