package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.DoorState;

public class DoorStateResponse {
    private DoorState result;

    public DoorStateResponse(){

    }

    public DoorStateResponse(DoorState result) {
        this.result = result;
    }

    public DoorState getResult() {
        return result;
    }

    public void setResult(DoorState result) {
        this.result = result;
    }
}
