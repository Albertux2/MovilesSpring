package com.example.moviles.model.ObjectMother;

import java.util.Random;

public class RandomNombre {
    private static Random random = new Random();


    public static String crearPalabra(int longitudPalabra) {
        int a = (int) 'a';
        char vocales[] = {'a','e','i','o','u'};
        String palabra = "";
        boolean vocal = false;
        for (int i = 0; i < longitudPalabra; i++) {
            if(vocal){
                palabra = palabra+(vocales[random.nextInt(vocales.length)]);
            }else{
                int letraNueva = (char) random.nextInt('z' - 'a' + 1);
                palabra = palabra+(char)(letraNueva+a);
            }
            vocal = !vocal;
        }
        return palabra;
    }
}
