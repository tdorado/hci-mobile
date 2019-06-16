package com.itba.hci.smarthome.model.entities;

public class BlindsState {
    private String status;
    private Integer level;

    public BlindsState(){

    }

    public BlindsState(String state, Integer level) {
        this.status = state;
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String state) {
        this.status = state;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
