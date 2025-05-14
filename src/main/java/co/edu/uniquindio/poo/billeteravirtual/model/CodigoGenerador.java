package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.Random;

public class CodigoGenerador {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generarCodigo6Chars() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }
}