package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.Random;

public class CodigoGenerador {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LONGITUD_CODIGO = 6;
    private static final int LONGITUD_ID = 10;
    private static final Random random = new Random();

    public static String generarCodigo() {
        return generarCadenaAleatoria(LONGITUD_CODIGO);
    }

    public static String generarId(String prefijo) {
        Validar.queNoNulo(prefijo, "El prefijo no puede ser nulo");
        return prefijo + "_" + generarCadenaAleatoria(LONGITUD_ID);
    }

    private static String generarCadenaAleatoria(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        for(int i = 0; i < longitud; i++) {
            int index = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(index));
        }
        return sb.toString();
    }
}