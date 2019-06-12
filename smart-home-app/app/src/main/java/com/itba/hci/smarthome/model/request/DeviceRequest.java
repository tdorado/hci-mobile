package com.itba.hci.smarthome.model.request;

public class DeviceRequest {
    private String typeId;
    private String name;
    private String meta;

    public DeviceRequest(String typeId, String name, String meta) {
        this.typeId = typeId;
        this.name = name;
        this.meta = meta;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
