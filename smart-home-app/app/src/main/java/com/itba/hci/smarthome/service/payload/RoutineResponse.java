package com.itba.hci.smarthome.service.payload;

public class RoutineResponse {
    private String id;
    private String name;
    private String meta;
    private Boolean failed = false;

    public RoutineResponse(){

    }

    public RoutineResponse(Boolean failed) {
        this.failed = failed;
    }

    public RoutineResponse(String id, String name, String meta) {
        this.id = id;
        this.name = name;
        this.meta = meta;
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
}
