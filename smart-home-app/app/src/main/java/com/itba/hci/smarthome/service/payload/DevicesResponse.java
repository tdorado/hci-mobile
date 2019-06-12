package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.Device;

import java.io.Serializable;
import java.util.List;

public class DevicesResponse implements Serializable {
    private List<Device> devices;

    public DevicesResponse(){

    }

    public DevicesResponse(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
