package com.example.moviles.model.ObjectMother;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovilRepo extends PagingAndSortingRepository<Movil,Long> {

    public List<Movil> findByModeloStartingWith(String inicioNombre);

    public List<Movil> findByPrecioLessThan(float precio);

    public List<Movil> findByBateria_CapacidadGreaterThan(int bateria);

    public List<Movil> findByRam_MemoriaGreaterThan(int memoria);

    public List<Movil> findByPantalla_PulgadasGreaterThan(float size);

    public List<Movil> findByAntutuGreaterThanEqualAndAntutuLessThanEqual(long max,long min);
}
