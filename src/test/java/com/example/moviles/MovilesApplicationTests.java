package com.example.moviles;

import com.example.moviles.model.Historico.HistorialRepo;
import com.example.moviles.model.Historico.HistoricoPrecio;
import com.example.moviles.model.ObjectMother.Movil;
import com.example.moviles.model.ObjectMother.MovilRepo;
import com.example.moviles.model.ObjectMother.ObjectMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.Random;


@SpringBootTest
class MovilesApplicationTests {

    @Autowired
    MovilRepo repo;
    @Autowired
    HistorialRepo historialRepo;
    Random random = new Random();
    int min = -100;
    int max = 100;
    ObjectMother movilMother = new ObjectMother();

    @Test
    void contextLoads() {
//        repo.saveAll(movilMother.generarMoviles(1000));
//        Iterable<Movil> moviles = repo.findAll();
//        moviles.forEach(movil -> {
//            for (int i = 2; i < 7; i++) {
//                movil.setPrecio(movil.getPrecio()+(random.nextInt(max-min)+min));
//                repo.save(movil);
//                historialRepo.save(new HistoricoPrecio(new Date(new Date().getTime() + (86400000*i)),movil));
//            }
//        });
    }

}
