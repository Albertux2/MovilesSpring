package com.example.moviles.model.ObjectMother;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Ram extends Componente{

    @Column(name = "RAM")
    private int memoria;

    public Ram(int memoria) {
        this.memoria = memoria;
        this.setPrecio(calcularPrecio());
    }

    public Ram() {

    }

    @Override
    public float calcularPrecio() {
        return memoria/300;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "memoria=" + memoria +
                '}';
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public int getMemoria() {
        return memoria;
    }
}
