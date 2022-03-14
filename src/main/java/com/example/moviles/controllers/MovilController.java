package com.example.moviles.controllers;

import com.example.moviles.model.Historico.HistoricoPrecio;
import com.example.moviles.model.ObjectMother.Movil;
import com.example.moviles.model.Response;
import com.example.moviles.model.movil.MovilDTO;
import com.example.moviles.model.movil.MovilFilter;
import com.example.moviles.services.MovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("moviles")
public class MovilController {
    @Autowired
    private MovilService movilService;

    @GetMapping("moviles")
    public ResponseEntity<Response> moviles(@RequestParam(required = false, name = "page", defaultValue = "0") int page) {
        Pageable paginaCon8 = PageRequest.of(page, 8, Sort.by("id").ascending());
        Response moviles_recuperados = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("moviles recuperados")
                .status(HttpStatus.OK)
                .data(Map.of("moviles", movilService.findAll(paginaCon8)))
                .build();
        return new ResponseEntity<Response>(moviles_recuperados, HttpStatus.OK);
    }

    @GetMapping("id")
    public Optional<Movil> buscaMovil(@RequestParam(name = "id") long id) {
        return movilService.findById(id);
    }

    @GetMapping("nombre")
    public ResponseEntity<Response> findByMarcaContainsOrModeloContains(@RequestParam("value") String value,
                                                                        @RequestParam(required = false, name = "page", defaultValue = "0") int page) {
        Pageable paginaCon8 = PageRequest.of(page, 8, Sort.by("id").ascending());
        Response moviles_recuperados = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("moviles recuperados")
                .status(HttpStatus.OK)
                .data(Map.of("moviles", movilService.findByMarcaContainsOrModeloContains(value, value, paginaCon8)))
                .build();
        System.out.println("Entro al controller");
        return new ResponseEntity<Response>(moviles_recuperados, HttpStatus.OK);
    }

    @PostMapping("filter")
    public ResponseEntity<Response> filter(@RequestBody MovilFilter filter
            , @RequestParam(required = false, name = "page", defaultValue = "0") int page
            , @RequestParam(required = false, name = "value", defaultValue = "") String value) {
        Response moviles_recuperados = Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("moviles recuperados")
                .status(HttpStatus.OK)
                .data(Map.of("moviles", movilService.findByFilter(value,page,filter)))
                .build();
        return new ResponseEntity<Response>(moviles_recuperados, HttpStatus.OK);
    }


    @GetMapping("idList")
    public List<Movil> idList(@RequestParam("ids") List<Long> ids) {
        return movilService.findByIdIn(ids);
    }

    @GetMapping("historico")
    public List<HistoricoPrecio> idList(@RequestParam("id") Long id) {
        return movilService.findHistorico(id);
    }

    @PostMapping("newMovil")
    public void anhadeMovil(@RequestBody Movil movil) {
        movilService.save(movil);
    }

    @PutMapping("update")
    public void update(long id, @RequestBody MovilDTO movil) {
        movilService.update(movil, id);
    }

}
