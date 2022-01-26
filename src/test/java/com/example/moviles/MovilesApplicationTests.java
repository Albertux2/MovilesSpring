package com.example.moviles;

import com.example.moviles.model.ObjectMother.Movil;
import com.example.moviles.model.ObjectMother.MovilRepo;
import com.example.moviles.model.ObjectMother.ObjectMother;
import net.bytebuddy.TypeCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@SpringBootTest
class MovilesApplicationTests {

    @Autowired
    MovilRepo repo;
    @Autowired
    HistorialRepo historialRepo;

    ObjectMother movilMother = new ObjectMother();

    @Test
    void contextLoads() {
        Iterable<Movil> moviles = repo.findAll();
        moviles.forEach(movil -> {
            historialRepo.save(new HistoricoPrecio(Date.from(Instant.now()),movil));
        });
    }

}
