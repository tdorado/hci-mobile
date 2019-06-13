package com.itba.hci.smarthome.model.entities;

public class Routine {
    private String id;
    private String name;
    private String meta;

    public Routine(){

    }

    public Routine(String id, String name, String meta) {
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
}
