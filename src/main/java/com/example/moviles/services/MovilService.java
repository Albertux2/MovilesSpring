package com.example.moviles.services;

import com.example.moviles.model.Historico.HistorialRepo;
import com.example.moviles.model.Historico.HistoricoPrecio;
import com.example.moviles.model.ObjectMother.Movil;
import com.example.moviles.model.ObjectMother.MovilRepo;
import com.example.moviles.model.movil.MovilDTO;
import com.example.moviles.model.movil.MovilFilter;
import com.example.moviles.model.movil.MovilMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovilService {
    @Autowired
    MovilRepo movilRepo;
    @Autowired
    HistorialRepo historicoRepo;

    MovilMapperImpl mapper = new MovilMapperImpl();

    public List<Movil> findByModeloStartingWith(String inicioNombre) {
        return movilRepo.findByModeloStartingWith(inicioNombre);
    }

    public List<Movil> findByPrecioLessThan(float precio) {
        return movilRepo.findByPrecioLessThan(precio);
    }

    public List<Movil> findByBateria_CapacidadGreaterThan(int bateria) {
        return movilRepo.findByBateria_CapacidadGreaterThan(bateria);
    }

    public List<Movil> findByRam_MemoriaGreaterThan(int memoria) {
        return movilRepo.findByRam_MemoriaGreaterThan(memoria);
    }

    public List<Movil> findByPantalla_PulgadasGreaterThan(float size) {
        return movilRepo.findByPantalla_PulgadasGreaterThan(size);
    }

    public Page<Movil> findAll(Pageable pageable) {
        return movilRepo.findAll(pageable);
    }

    public <S extends Movil> S save(S entity) {
        return movilRepo.save(entity);
    }

    public Optional<Movil> findById(Long aLong) {
        return movilRepo.findById(aLong);
    }

    public List<Movil> findByAntutuGreaterThanEqualAndAntutuLessThanEqual(long max, long min) {
        return movilRepo.findByAntutuGreaterThanEqualAndAntutuLessThanEqual(max, min);
    }

    public Page<Movil> findByMarcaContainsOrModeloContains(String marca,String modelo, Pageable pageable){
        return movilRepo.findByMarcaContainingOrModeloContaining(marca,modelo,pageable);
    }

    public List<Movil> findByMarcaContainsOrModeloContains(String marca,String modelo){
        return movilRepo.findByMarcaContainingOrModeloContaining(marca,modelo);
    }

    public List<Movil> findByIdIn(List<Long> ids){
        return this.movilRepo.findByIdIn(ids);
    }

    public List<HistoricoPrecio> findHistorico(Long id){
        return this.historicoRepo.findByMovil_Id(id);
    }

    public Page<Movil> findByFilter(String value, int page, MovilFilter filter){
        List<Movil> moviles = findByMarcaContainsOrModeloContains(value,value);
        return listToPage(filter.filter(moviles),page);
    }

    public void update(MovilDTO dto, long id){
        Movil movil = movilRepo.findById(id).get();
        mapper.updateMovilFromDto(dto,movil);
        movilRepo.save(movil);
    }

    private Page<Movil> listToPage(List<Movil> list, int page){
        Pageable paginaCon8 = PageRequest.of(page, 8, Sort.by("id").ascending());
        // Skipea los elementos de las páginas anteriores y lo limita al tamaño del pageable
        List<Movil> paged = list.parallelStream()
                .skip(paginaCon8.getPageSize() * paginaCon8.getPageNumber())
                .limit(paginaCon8.getPageSize())
                .collect(Collectors.toList());
        return new PageImpl(paged,paginaCon8,list.size());
    }
}
