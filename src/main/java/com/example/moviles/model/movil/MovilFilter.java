package com.example.moviles.model.movil;

import com.example.moviles.model.ObjectMother.Movil;
import com.example.moviles.model.Utils.Range;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovilFilter {
    Range precioRange;
    Range ramRange;
    int antutuMin;

    public MovilFilter(Range precioRange, Range ramRange, int antutuMin) {
        this.precioRange = precioRange;
        this.ramRange = ramRange;
        this.antutuMin = antutuMin;
    }

    public MovilFilter() {

    }

    public List<Movil> filter(List<Movil> moviles) {
        List<Movil> filtered = moviles.parallelStream()
                .filter((movil) -> movil.getPrecio() >= precioRange.getMin() && movil.getPrecio() <= precioRange.getMax())
                .filter((movil) -> movil.getRam().getMemoria() >= ramRange.getMin() && movil.getRam().getMemoria() <= ramRange.getMax())
                .filter((movil) -> movil.getAntutu() >= antutuMin)
                .collect(Collectors.toList());
        return filtered;
    }


    public Range getPrecioRange() {
        return precioRange;
    }

    public void setPrecioRange(Range precioRange) {
        this.precioRange = precioRange;
    }

    public Range getRamRange() {
        return ramRange;
    }

    public void setRamRange(Range ramRange) {
        this.ramRange = ramRange;
    }

    public int getAntutuMin() {
        return antutuMin;
    }

    @Override
    public String toString() {
        return "MovilFilter{" +
                "precioRange=" + precioRange +
                ", ramRange=" + ramRange +
                ", antutuMin=" + antutuMin +
                '}';
    }

    public void setAntutuMin(int antutuMin) {
        this.antutuMin = antutuMin;
    }
}
