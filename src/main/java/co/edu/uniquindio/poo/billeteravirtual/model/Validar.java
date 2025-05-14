package co.edu.uniquindio.poo.billeteravirtual.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Clase utilitaria para validaciones comunes en el sistema.
 * Implementa el principio DRY (Don't Repeat Yourself) para validaciones.
 */
public final class Validar {

    // Constructor privado para evitar instanciación
    private Validar() {}

    // Validaciones básicas
    public static void que(boolean condicion, String mensajeError) {
        if (!condicion) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    public static void queNoNulo(Object objeto, String mensajeError) {
        if (objeto == null) {
            throw new NullPointerException(mensajeError);
        }
    }

    public static void queNoVacio(String texto, String mensajeError) {
        queNoNulo(texto, mensajeError);
        if (texto.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    // Validaciones numéricas
    public static void quePositivo(Number numero, String mensajeError) {
        queNoNulo(numero, "El número no puede ser nulo");
        if (numero.doubleValue() <= 0) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    public static void queRango(double valor, double min, double max, String mensajeError) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    // Validaciones específicas del dominio
    public static void queCedulaValida(String cedula) {
        queNoVacio(cedula, "La cédula no puede estar vacía");

        // Validación básica para cedula
        if (!cedula.matches("^[0-9]{8,10}$")) {
            throw new IllegalArgumentException("Formato de cédula inválido");
        }
    }

    public static void queCorreoValido(String correo) {
        queNoVacio(correo, "El correo no puede estar vacío");

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!correo.matches(regex)) {
            throw new IllegalArgumentException("Formato de correo electrónico inválido");
        }
    }

    public static void queTelefonoValido(String telefono) {
        queNoVacio(telefono, "El teléfono no puede estar vacío");

        String regex = "^\\+?[0-9\\s]{10,15}$";
        if (!telefono.replaceAll("\\s", "").matches(regex)) {
            throw new IllegalArgumentException("Formato de teléfono inválido");
        }
    }

    public static void queContraseniaSegura(String contrasenia) {
        queNoVacio(contrasenia, "La contraseña no puede estar vacía");

        // Requisitos: 8+ caracteres, 1 mayúscula, 1 número
        if (contrasenia.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!contrasenia.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula");
        }
        if (!contrasenia.matches(".*\\d.*")) {
            throw new IllegalArgumentException("La contraseña debe contener al menos un número");
        }
    }

    // Validaciones de fechas
    public static void queFechaAnterior(LocalDateTime fecha, LocalDateTime limite, String mensaje) {
        queNoNulo(fecha, "La fecha no puede ser nula");
        if (fecha.isAfter(limite)) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    // Validaciones de colecciones
    public static <T> void queNoVacia(Collection<T> coleccion, String mensaje) {
        queNoNulo(coleccion, "La colección no puede ser nula");
        if (coleccion.isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    // Validación de estado
    public static void queEstadoValido(String estado, Set<String> estadosValidos) {
        queNoVacio(estado, "El estado no puede estar vacío");
        queNoVacia(estadosValidos, "Debe proporcionar estados válidos");

        if (!estadosValidos.contains(estado)) {
            throw new IllegalArgumentException("Estado no válido: " + estado);
        }
    }

    // Validación de objetos del dominio
    public static void queUsuarioValido(Usuario usuario) {
        queNoNulo(usuario, "El usuario no puede ser nulo");
        que(usuario.getEsCuentaActiva(), "El usuario debe estar activo");
    }

    public static void queTransaccionValida(Transaccion transaccion) {
        queNoNulo(transaccion, "La transacción no puede ser nula");
        quePositivo(transaccion.getMonto(), "El monto debe ser positivo");
    }
}