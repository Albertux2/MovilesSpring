package com.example.moviles.model.Historico;

import com.example.moviles.model.ObjectMother.Movil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HistorialRepo extends PagingAndSortingRepository<HistoricoPrecio,Long> {
    public List<HistoricoPrecio> findByMovil_Id(Long id);
}
