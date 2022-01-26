package com.example.moviles;

import com.example.moviles.model.ObjectMother.Movil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("moviles")
public class MovilController {

    @Autowired
    private MovilService movilService;

    @GetMapping("home")
    public String bienvenida() {
        return "Inicio de la pagina";
    }

    @GetMapping("moviles")
    public Iterable<Movil> moviles(@RequestParam(required = false, name = "page", defaultValue = "0") int page) {
        Pageable paginaCon20 = PageRequest.of(page, 20, Sort.by("id").ascending());
        return this.movilService.findAll(paginaCon20);
    }

    @GetMapping("id")
        public Optional<Movil> buscaMovil(@RequestParam(name="id") long id){
            return movilService.findById(id);
        }

    @GetMapping("modelo")
    public List<Movil> buscaModelo(@RequestParam("modelo") String inicial) {
        return movilService.findByModeloStartingWith((inicial));
    }

    @GetMapping("precioMax")
    public List<Movil> buscaPorPrecioMax(@RequestParam("precio") float precio) {
        return movilService.findByPrecioLessThan(precio);
    }

    @GetMapping("ramMinima")
    public List<Movil> buscaPorRamMin(@RequestParam("ram") int ram) {
        return movilService.findByRam_MemoriaGreaterThan(ram);
    }

    @GetMapping("bateriaMinima")
    public List<Movil> buscaPorbateriaMin(@RequestParam("bateria") int bateria) {
        return movilService.findByBateria_CapacidadGreaterThan(bateria);
    }

    @GetMapping("pulgadasMinima")
    public List<Movil> buscaPorPulgadasMin(@RequestParam("pulgadas") float pulgadas){
        return movilService.findByPantalla_PulgadasGreaterThan(pulgadas);
    }

    @GetMapping("antutuMinMax")
    public List<Movil> buscaPorPulgadasMin(@RequestParam("min") long min,@RequestParam("max") long max){
        return movilService.findByAntutuGreaterThanEqualAndAntutuLessThanEqual(min,max);
    }

    @PostMapping("newMovil")
    public void anhadeMovil(@RequestBody Movil movil){
        movilService.save(movil);
    }

    @PutMapping("update")
    public void update(long id,@RequestBody MovilDTO movil){
        movilService.update(movil,id);
    }

}
