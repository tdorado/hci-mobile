package com.itba.hci.smarthome.model.entities;

public class BlindsState {
    private String state;
    private Integer level;

    public BlindsState(){

    }

    public BlindsState(String state, Integer level) {
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
