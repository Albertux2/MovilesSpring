package com.example.moviles;

import com.example.moviles.model.ObjectMother.Bateria;
import com.example.moviles.model.ObjectMother.Pantalla;
import com.example.moviles.model.ObjectMother.Procesador;
import com.example.moviles.model.ObjectMother.Ram;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MovilDTO {
    private String marca;
    private String modelo;
    private Bateria bateria;
    private Pantalla pantalla;
    private Ram ram;
    private Procesador procesador;
    private Long antutu;
    private Float precio;
    private Long id;

    public Bateria getBateria() {
        return bateria;
    }

    public void setBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    public Pantalla getPantalla() {
        return pantalla;
    }

    public void setPantalla(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Procesador getProcesador() {
        return procesador;
    }

    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getAntutu() {
        return antutu;
    }

    public void setAntutu(long antutu) {
        this.antutu = antutu;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
