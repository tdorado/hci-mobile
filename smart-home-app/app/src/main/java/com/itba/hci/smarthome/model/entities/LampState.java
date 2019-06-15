package com.itba.hci.smarthome.model.entities;

public class LampState {
    private String status;
    private String color;
    private Integer brightness;

    public LampState(){

    }

    public LampState(String status, String color, Integer brightness) {
        this.status = status;
        this.color = color;
        this.brightness = brightness;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }
}
