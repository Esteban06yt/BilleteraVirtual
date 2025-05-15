package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.repository.TransaccionRepository;
import java.util.Random;

public class CodigoGenerador {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LONGITUD_CODIGO = 6;
    private static final int LONGITUD_ID = 10;
    private static final Random random = new Random();
    private static TransaccionRepository transaccionRepository;

    public static void setTransaccionRepository(TransaccionRepository repository) {
        transaccionRepository = repository;
    }

    public static String generarCodigo() {
        return generarCadenaAleatoria(LONGITUD_CODIGO);
    }

    public static String generarId(String prefijo) {
        Validar.queNoNulo(prefijo, "El prefijo no puede ser nulo");
        Validar.queNoNulo(transaccionRepository, "El repositorio de transacciones no ha sido configurado");

        String idGenerado;
        int intentos = 0;
        final int MAX_INTENTOS = 5;

        do {
            idGenerado = prefijo + "_" + generarCadenaAleatoria(LONGITUD_ID);
            intentos++;

            if (intentos >= MAX_INTENTOS) {
                throw new IllegalStateException("No se pudo generar un ID único después de " + MAX_INTENTOS + " intentos");
            }

        } while (transaccionRepository.existeIdTransaccion(idGenerado));

        return idGenerado;
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