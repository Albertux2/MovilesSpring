package com.example.moviles.model.ObjectMother;

import javax.persistence.Embeddable;

@Embeddable
public class Pantalla extends Componente{

    private float pulgadas;

    public Pantalla(float pulgadas) {
        this.pulgadas = (float)Math.floor(pulgadas * 100)/100;
        this.setPrecio(this.calcularPrecio());
    }

    public Pantalla() {

    }

    @Override
    public float calcularPrecio() {
        return pulgadas*6;
    }

	public float getPulgadas() {
		return pulgadas;
	}

    public void setPulgadas(float pulgadas) {
        this.pulgadas = pulgadas;
    }

    @Override
    public String toString() {
        return "Pantalla{" +
                "pulgadas=" + pulgadas +
                '}';
    }
}
