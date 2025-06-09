package com.veterinaria.usuarios.model;

public enum Role {
    USER("Usuario"),
    ADMIN("Administrador"),
    VETERINARIO("Veterinario"),
    PROPIETARIO("Propietario");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}