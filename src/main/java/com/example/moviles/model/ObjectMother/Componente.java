package com.example.moviles.model.ObjectMother;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Componente {
    @JsonIgnore
    private float precio;

    public abstract float calcularPrecio();

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
