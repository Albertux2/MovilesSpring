package com.example.moviles.model.ObjectMother;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Bateria extends Componente {
    @Column(name = "bateria")
    private int capacidad;

    public Bateria(int capacidad) {
        this.capacidad = capacidad;
        this.setPrecio(this.calcularPrecio());
    }

    public Bateria() {

    }

    @Override
    public float calcularPrecio() {
        return capacidad/100;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Bateria{" +
                "capacidad=" + capacidad +
                '}';
    }
}