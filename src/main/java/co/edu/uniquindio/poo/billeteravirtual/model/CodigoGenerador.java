package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.repository.TransaccionRepository;

import java.util.Random;

public class CodigoGenerador {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMEROS = "0123456789";
    private static final int LONGITUD_CODIGO = 6;
    private static final int LONGITUD_ID = 10;
    private static final Random random = new Random();
    private static TransaccionRepository transaccionRepository;

    public static void setTransaccionRepository(TransaccionRepository repository) {
        transaccionRepository = repository;
    }

    // Generar un código aleatorio de longitud 6
    public static String generarCodigo() {
        return generarCadenaAleatoria(LONGITUD_CODIGO);
    }

    // Generar un ID de transacción único con un prefijo
    public static String generarIdTransaccion(String prefijo) {
        Validar.queNoNulo(prefijo, "El prefijo no puede ser nulo");
        Validar.queNoNulo(transaccionRepository, "El repositorio de transacciones no ha sido configurado");

        String idGenerado;
        do {
            idGenerado = prefijo + "_" + generarCadenaAleatoria(LONGITUD_ID); // Genera un nuevo ID con el prefijo
        } while (transaccionRepository.existeIdTransaccion(idGenerado)); // Verifica si ya existe en el repositorio

        return idGenerado; // Retorna el ID único
    }

    // Generar un ID único sin prefijo
    public static String generarId() {
        Validar.queNoNulo(transaccionRepository, "El repositorio de transacciones no ha sido configurado");

        String idGenerado;
        do {
            idGenerado = generarCadenaAleatoria(LONGITUD_ID); // Genera un nuevo ID sin prefijo
        } while (transaccionRepository.existeIdTransaccion(idGenerado)); // Verifica si ya existe en el repositorio

        return idGenerado; // Retorna el ID único
    }

    // Generar un ID de cuenta único (Integer)
    public static Integer generarIdCuenta() {
        Validar.queNoNulo(transaccionRepository, "El repositorio de transacciones no ha sido configurado");

        Integer idGenerado;
        do {
            // Genera un nuevo ID para cuenta como número
            idGenerado = Integer.parseInt(generarCadenaAleatoriaNumeros(LONGITUD_ID));
        } while (transaccionRepository.existeIdTransaccion(idGenerado.toString())); // Verifica si ya existe en el repositorio

        return idGenerado; // Retorna el ID único como Integer
    }

    // Generar una cadena aleatoria con números
    private static String generarCadenaAleatoriaNumeros(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(NUMEROS.length());
            sb.append(NUMEROS.charAt(index)); // Añade un número aleatorio
        }
        return sb.toString();
    }


    // Generar una cadena aleatoria con caracteres alfanuméricos
    private static String generarCadenaAleatoria(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(index)); // Añade un caracter aleatorio
        }
        return sb.toString();
    }
}