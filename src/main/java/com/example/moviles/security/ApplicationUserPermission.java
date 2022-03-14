package com.example.moviles.security;

public enum ApplicationUserPermission {
    MOVIL_READ("movil:read"), MOVIL_WRITE("movil:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
