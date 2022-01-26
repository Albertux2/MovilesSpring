package com.example.moviles;

import com.example.moviles.model.ObjectMother.Movil;
import com.example.moviles.model.ObjectMother.MovilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovilService {
    @Autowired
    MovilRepo movilRepo;
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

    public void update(MovilDTO dto, long id){
        Movil movil = movilRepo.findById(id).get();
        mapper.updateMovilFromDto(dto,movil);
        movilRepo.save(movil);

    }
}
