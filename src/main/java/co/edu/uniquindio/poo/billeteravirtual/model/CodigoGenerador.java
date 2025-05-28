package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.List;
import java.util.Random;

public class CodigoGenerador {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMEROS = "0123456789";
    private static final int LONGITUD_CODIGO = 6;
    private static final int LONGITUD_ID = 10;
    private static final Random random = new Random();
    private static List<Transaccion> transacciones;

    public static void setTransacciones(List<Transaccion> listaTransacciones) {
        transacciones = listaTransacciones;
    }

    public static String generarCodigo() {
        return generarCadenaAleatoria(LONGITUD_CODIGO);
    }

    public static String generarIdTransaccion(String prefijo) {
        Validar.queNoNulo(prefijo, "El prefijo no puede ser nulo");
        Validar.queNoNulo(transacciones, "La lista de transacciones no ha sido configurada");

        String idGenerado;
        do {
            idGenerado = prefijo + "_" + generarCadenaAleatoria(LONGITUD_ID);
        } while (existeIdTransaccion(idGenerado));

        return idGenerado;
    }

    public static String generarId() {
        Validar.queNoNulo(transacciones, "La lista de transacciones no ha sido configurada");

        String idGenerado;
        do {
            idGenerado = generarCadenaAleatoria(LONGITUD_ID);
        } while (existeIdTransaccion(idGenerado));

        return idGenerado;
    }

    public static Integer generarIdCuenta() {
        Validar.queNoNulo(transacciones, "La lista de transacciones no ha sido configurada");

        Integer idGenerado;
        do {
            idGenerado = Integer.parseInt(generarCadenaAleatoriaNumeros(LONGITUD_ID));
        } while (existeIdTransaccion(idGenerado.toString()));

        return idGenerado;
    }

    private static boolean existeIdTransaccion(String id) {
        return transacciones.stream()
                .anyMatch(t -> t != null && id.equals(t.getIdTransaccion()));
    }

    private static String generarCadenaAleatoriaNumeros(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(NUMEROS.length());
            sb.append(NUMEROS.charAt(index));
        }
        return sb.toString();
    }

    private static String generarCadenaAleatoria(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(index));
        }
        return sb.toString();
    }
}