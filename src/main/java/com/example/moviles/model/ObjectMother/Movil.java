package com.example.moviles.model.ObjectMother;

import com.example.moviles.HistoricoPrecio;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Movil {
    private String marca;
    private String modelo;
    @Embedded
    private Bateria bateria;
    @Embedded
    private Pantalla pantalla;
    @Embedded
    private Ram ram;
    @Embedded
    private Procesador procesador;
    private Long antutu;
    private Float precio;
    @OneToMany(mappedBy = "movil")
    @JsonIgnore
    private List<HistoricoPrecio> precios;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Movil(Bateria bateria, Pantalla pantalla, Ram ram, Procesador procesador, String marca, String modelo) {
        this.bateria = bateria;
        this.pantalla = pantalla;
        this.ram = ram;
        this.procesador = procesador;
        this.precioTotal();
        this.calcularAntutu();
        this.marca = marca;
        this.modelo = modelo;
    }

    public Movil() {

    }

    public void precioTotal() {
        this.setPrecio(ram.getPrecio() + procesador.getPrecio() + bateria.getPrecio() + pantalla.getPrecio());
    }

    public void calcularAntutu() {
        this.antutu = (long) (procesador.getNucleos() * (procesador.getFrecuencia() + ram.getMemoria()) * 4);
    }

    @Override
    public String toString() {
        return "Movil{" +
                "marca=" + marca +
                ", modelo=" + modelo +
                ", bateria=" + bateria.toString() +
                ", pantalla=" + pantalla.toString() +
                ", ram=" + ram.toString() +
                ", procesador=" + procesador.toString() +
                ", antutu=" + antutu +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movil)) return false;
        Movil movil = (Movil) o;
        return getMarca().equals(movil.getMarca()) && getModelo().equals(movil.getModelo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBateria(), getPantalla(), getRam(), getProcesador(), getAntutu(), getPrecio());
    }

    public List<HistoricoPrecio> getPrecios() {
        return precios;
    }

    public void setPrecios(List<HistoricoPrecio> precios) {
        this.precios = precios;
    }

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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public long getAntutu() {
        return antutu;
    }

    public void setAntutu(long antutu) {
        this.antutu = antutu;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

