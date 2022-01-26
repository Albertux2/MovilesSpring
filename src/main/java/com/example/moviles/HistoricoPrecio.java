package com.example.moviles;

import com.example.moviles.model.ObjectMother.Movil;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HistoricoPrecio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="movil_id")
    private Movil movil;
    private float precio;
    private Date fecha;


    public HistoricoPrecio() {
    }

    public HistoricoPrecio(Date fecha,Movil movil) {
        this.precio = movil.getPrecio();
        this.fecha = fecha;
        this.movil = movil;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
