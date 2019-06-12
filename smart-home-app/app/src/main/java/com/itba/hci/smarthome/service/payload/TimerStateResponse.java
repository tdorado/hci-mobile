package com.itba.hci.smarthome.service.payload;

public class TimerStateResponse {
    private String status;
    private Integer interval;
    private Integer remaining;

    public TimerStateResponse(){

    }

    public TimerStateResponse(String status, Integer interval, Integer remaining) {
        this.status = status;
        this.interval = interval;
        this.remaining = remaining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
