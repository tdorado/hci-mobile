package com.itba.hci.smarthome.service.payload;

public class BlindsStateResponse {
    private String state;
    private Integer level;

    public BlindsStateResponse(){

    }

    public BlindsStateResponse(String state, Integer level) {
        this.state = state;
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
