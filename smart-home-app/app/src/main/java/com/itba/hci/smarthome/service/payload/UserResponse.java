package com.itba.hci.smarthome.service.payload;

public class UserResponse {
    private boolean logged = false;
    private String rol = "";
    private Long id;

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    private String direccion;
    public UserResponse(boolean logged, String rol, Long id, String direccion) {
        this.logged = logged;
        this.rol = rol;
        this.id = id;
        this.direccion = direccion;
    }

    public boolean getLogged() {
        return logged;
    }
    public String getRol() { return rol; }
}
