package com.itba.hci.smarthome.service.payload;

public class StringResultResponse {
    private String result;

    public StringResultResponse(){

    }

    public StringResultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
