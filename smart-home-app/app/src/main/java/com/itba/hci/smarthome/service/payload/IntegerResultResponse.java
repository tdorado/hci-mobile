package com.itba.hci.smarthome.service.payload;

public class IntegerResultResponse {
    private Integer result;

    public IntegerResultResponse(){

    }

    public IntegerResultResponse(Integer result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
