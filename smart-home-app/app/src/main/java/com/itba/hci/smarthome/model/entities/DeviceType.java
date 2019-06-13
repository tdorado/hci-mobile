package com.itba.hci.smarthome.model.entities;

import java.util.List;

public class DeviceType {
    private String id;
    private String name;
    private List<Action> actions;
    private List<Event> events;

    public DeviceType(){

    }

    public DeviceType(String id, String name, List<Action> actions, List<Event> events) {
        this.id = id;
        this.name = name;
        this.actions = actions;
        this.events = events;
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
