package com.itba.hci.smarthome.service.payload;

public class RefrigeratorStateResponse {
    private Integer freezerTemperature;
    private Integer temperature;
    private String mode;

    public RefrigeratorStateResponse(){

    }

    public RefrigeratorStateResponse(Integer freezerTemperature, Integer temperature, String mode) {
        this.freezerTemperature = freezerTemperature;
        this.temperature = temperature;
        this.mode = mode;
    }

    public Integer getFreezerTemperature() {
        return freezerTemperature;
    }

    public void setFreezerTemperature(Integer freezerTemperature) {
        this.freezerTemperature = freezerTemperature;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
