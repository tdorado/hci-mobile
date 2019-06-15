package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.BlindsState;

public class BlindsStateResponse {
    private BlindsState result;

    public BlindsStateResponse(){

    }

    public BlindsStateResponse(BlindsState result) {
        this.result = result;
    }

    public BlindsState getResult() {
        return result;
    }

    public void setResult(BlindsState result) {
        this.result = result;
    }
}
