package com.itba.hci.smarthome.service.payload;

import java.util.Objects;

public class DeviceResponse {
    private String id;
    private String name;
    private String typeId;
    private String meta;
    private Boolean failed = false;

    public DeviceResponse(){

    }

    public DeviceResponse(Boolean failed) {
        this.failed = failed;
    }

    public DeviceResponse(String id, String name, String typeId, String meta) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.meta = meta;
        this.failed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceResponse that = (DeviceResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
