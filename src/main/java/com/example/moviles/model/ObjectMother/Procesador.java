package com.example.moviles.model.ObjectMother;

import javax.persistence.Embeddable;

@Embeddable
public class Procesador extends Componente {
    private int nucleos;
    private float frecuencia;

    public Procesador(int nucleos, float frecuencia) {
        super();
        this.nucleos = nucleos;
        this.frecuencia = frecuencia;
        this.setPrecio(this.calcularPrecio());
    }

    public Procesador() {

    }

    @Override
    public float calcularPrecio() {
    	float frecuanciaNucleo= frecuencia/100*(nucleos+2);
        return frecuanciaNucleo;
    }

    public int getNucleos() {
        return nucleos;
    }

    public void setNucleos(int nucleos) {
        this.nucleos = nucleos;
    }

    public float getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(float frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "nucleos=" + nucleos +
                ", frecuencia=" + frecuencia +
                '}';
    }
}
