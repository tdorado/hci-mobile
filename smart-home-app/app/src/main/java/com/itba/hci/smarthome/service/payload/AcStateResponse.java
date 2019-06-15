package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.AcState;

public class AcStateResponse {
    private AcState result;

    public AcStateResponse(){

    }

    public AcStateResponse(AcState result) {
        this.result = result;
    }

    public AcState getResult() {
        return result;
    }

    public void setResult(AcState result) {
        this.result = result;
    }
}
