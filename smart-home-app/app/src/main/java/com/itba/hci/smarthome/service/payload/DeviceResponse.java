package com.itba.hci.smarthome.service.payload;

import com.itba.hci.smarthome.model.entities.Device;

import java.io.Serializable;

public class DeviceResponse implements Serializable {
    private Device device;

    public DeviceResponse(){

    }

    public DeviceResponse(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
