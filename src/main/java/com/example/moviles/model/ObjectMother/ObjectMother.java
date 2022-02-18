package com.example.moviles.model.ObjectMother;

import java.util.ArrayList;
import java.util.Random;

public class ObjectMother {
    private final int MAX_NUCLEOS = 11;
    Random random = new Random();
    private String marcas[] = {"Alpha","Omega","Delta","Doko","Turbo","Dovux","LG","Xiaomi","Sony","Realme","Apple","Samsung","Acer","Alcatel","Asus","BlackShark","BlackBerry","Blackview","Cubot","Google","Hisense","HTC","Huawei","Lenovo","Meizu","Motorola","Nokia","Nubia","Oppo","Poco","Razer","THL","TCL","Ulefone","Vivo","Redmi","ZTE"};

    public ArrayList<Movil> generarMoviles(int cantidad) {
        ArrayList<Movil> moviles = new ArrayList<Movil>();
        boolean repetido;
        for (int i = 0; i < cantidad; i++) {
            do{
                Movil movil = generarMovil();
                repetido = moviles.stream().anyMatch((m) ->
                        m.equals(movil)
                );
            }while(repetido);
            moviles.add(generarMovil());
        }
        return moviles;
    }

    public Movil generarMovil() {
        Procesador procesador = calcularProcesador();
        Ram ram = new Ram(calcularRam(procesador));
        Pantalla pantalla = new Pantalla(calcularPantalla(procesador));
        Bateria bateria = calcularBateria(pantalla);
        return new Movil(bateria, pantalla, ram, procesador, marcas[random.nextInt(marcas.length-1)], RandomNombre.crearPalabra(5));
    }

    private int calcularRam(Procesador procesador) {
        int medioGB = 512;
        return procesador.getNucleos() * medioGB + random.nextInt(procesador.getNucleos() / 2) * medioGB;
    }

    private Procesador calcularProcesador() {
        int nucleos = getNucleosRandom() + 1;
        int frecuencia = calcularFrecuencia(nucleos);
        Procesador procesador = new Procesador(nucleos, frecuencia);
        return procesador;
    }

    private int getNucleosRandom() {
        return random.nextInt(MAX_NUCLEOS) + 1;
    }

    private int calcularFrecuencia(int nucleos) {
        if (nucleos <= 4) {
            return nucleos * 450;
        } else if (nucleos <= 8) {
            return nucleos * 250;
        } else {
            return nucleos * 180;
        }
    }

    private float calcularPantalla(Procesador procesador) {
        if (procesador.getNucleos() <= 4) {
            return (float) (4 + random.nextDouble() * (6 - 4));
        } else {
            return (float) (5.6 + random.nextDouble() * (7 - 5.6));
        }
    }

    private Bateria calcularBateria(Pantalla pantalla) {
        int mwhMinimoPorPulgada = 575;
        int variacionBateria = 150;
        int mhwPorPulgada = mwhMinimoPorPulgada + random.nextInt(variacionBateria);
        return new Bateria((int) pantalla.getPulgadas() * mhwPorPulgada);
    }

    public static void main(String[] args) {
        ObjectMother o = new ObjectMother();
        o.generarMoviles(1000).forEach((e) -> {
            System.out.println(e.toString());
        });
    }
}
