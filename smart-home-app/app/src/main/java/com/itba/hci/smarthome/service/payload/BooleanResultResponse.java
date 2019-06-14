package com.itba.hci.smarthome.service.payload;

public class BooleanResultResponse {
    private Boolean result;

    public BooleanResultResponse(){

    }

    public BooleanResultResponse(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
