package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorTransacciones {

    private List<Transaccion> transacciones;

    // Constructor
    public GestorTransacciones() {
        this.transacciones = new ArrayList<>();
    }

    // Metodo para agregar una transacción
    public void agregarTransaccion(Transaccion transaccion) {
        // Asignar la fecha y hora actual a la transacción
        transaccion.setFechaHora(LocalDateTime.now());

        // Validar que la transacción no sea nula
        Validar.queNoNulo(transaccion, "La transacción no puede ser nula");
        Validar.queTransaccionValida(transaccion);

        // Verificar que la transacción tenga un monto positivo
        if (transaccion.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto de la transacción debe ser positivo.");
        }

        // Verificar que el emisor tiene suficiente saldo en caso de transferencias o pagos
        if (transaccion.getTipo() == TipoTransaccion.TRANSFERENCIA || transaccion.getTipo() == TipoTransaccion.PAGO) {
            if (transaccion.getEmisor().getSaldo() < transaccion.getMonto()) {
                throw new IllegalArgumentException("El emisor no tiene suficiente saldo.");
            }
        }

        // Agregar la transacción a la lista
        transacciones.add(transaccion);

        // Actualizar el saldo de los usuarios según el tipo de transacción
        switch (transaccion.getTipo()) {
            case TRANSFERENCIA:
                // Transferencia: el emisor pierde el dinero y el destinatario lo recibe
                transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() - transaccion.getMonto());
                transaccion.getDestinatario().setSaldo(transaccion.getDestinatario().getSaldo() + transaccion.getMonto());
                break;

            case RETIRO:
                // Retiro: el emisor pierde el dinero
                transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() - transaccion.getMonto());
                break;

            case DEPOSITO:
                // Depósito: el emisor (usualmente quien recibe el depósito) gana dinero
                transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() + transaccion.getMonto());
                break;

            case PAGO:
                // Pago: el emisor paga el servicio, pierde el dinero
                transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() - transaccion.getMonto());
                break;

            case RECARGA:
                // Recarga: el emisor (usualmente quien recarga) gana el monto
                transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() + transaccion.getMonto());
                break;

            default:
                throw new IllegalArgumentException("Tipo de transacción desconocido.");
        }
    }

    // Metodo para eliminar una transacción por ID
    public void eliminarTransaccion(String idTransaccion) {
        Validar.queNoVacio(idTransaccion, "El ID de la transacción no puede estar vacío");

        Transaccion transaccion = buscarTransaccionPorId(idTransaccion);
        if (transaccion != null) {
            transacciones.remove(transaccion);

            // Si la transacción era una transferencia o pago, revertir el saldo
            switch (transaccion.getTipo()) {
                case TRANSFERENCIA:
                    // Revertir una transferencia
                    transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() + transaccion.getMonto());
                    transaccion.getDestinatario().setSaldo(transaccion.getDestinatario().getSaldo() - transaccion.getMonto());
                    break;

                case RETIRO:
                    // Revertir un retiro
                    transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() + transaccion.getMonto());
                    break;

                case DEPOSITO:
                    // Revertir un depósito
                    transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() - transaccion.getMonto());
                    break;

                case PAGO:
                    // Revertir un pago
                    transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() + transaccion.getMonto());
                    break;

                case RECARGA:
                    // Revertir una recarga
                    transaccion.getEmisor().setSaldo(transaccion.getEmisor().getSaldo() - transaccion.getMonto());
                    break;
            }
        } else {
            throw new IllegalArgumentException("La transacción con ID " + idTransaccion + " no existe.");
        }
    }

    // Metodo para buscar una transacción por su ID
    public Transaccion buscarTransaccionPorId(String idTransaccion) {
        Validar.queNoVacio(idTransaccion, "El ID de la transacción no puede estar vacío");
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getIdTransaccion().equals(idTransaccion)) {
                return transaccion;
            }
        }
        return null; // Si no se encuentra
    }

    // Metodo para listar todas las transacciones
    public List<Transaccion> listarTransacciones() {
        return new ArrayList<>(transacciones); // Devolvemos una copia de la lista
    }

    // Metodo para obtener todas las transacciones de un usuario
    public List<Transaccion> obtenerTransaccionesPorUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        List<Transaccion> transaccionesUsuario = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getEmisor().equals(usuario) || transaccion.getDestinatario().equals(usuario)) {
                transaccionesUsuario.add(transaccion);
            }
        }
        return transaccionesUsuario;
    }

    // Metodo para obtener las transacciones por tipo (por ejemplo, solo depósitos, solo retiros, etc.)
    public List<Transaccion> obtenerTransaccionesPorTipo(TipoTransaccion tipo) {
        Validar.queNoNulo(tipo, "El tipo de transacción no puede ser nulo");

        List<Transaccion> transaccionesPorTipo = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == tipo) {
                transaccionesPorTipo.add(transaccion);
            }
        }
        return transaccionesPorTipo;
    }

    // Metodo para filtrar transacciones por monto (rango de montos)
    public List<Transaccion> filtrarTransaccionesPorMonto(Double montoMinimo, Double montoMaximo) {
        Validar.quePositivo(montoMinimo, "El monto mínimo debe ser positivo");
        Validar.quePositivo(montoMaximo, "El monto máximo debe ser positivo");

        List<Transaccion> transaccionesFiltradas = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getMonto() >= montoMinimo && transaccion.getMonto() <= montoMaximo) {
                transaccionesFiltradas.add(transaccion);
            }
        }
        return transaccionesFiltradas;
    }

    // Metodo para obtener el número de transacciones de un usuario
    public int obtenerNumeroTransaccionesDeUsuario(Usuario usuario) {
        Validar.queNoNulo(usuario, "El usuario no puede ser nulo");

        int count = 0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getEmisor().equals(usuario) || transaccion.getDestinatario().equals(usuario)) {
                count++;
            }
        }
        return count;
    }

    // Metodo para obtener el saldo total de todas las transacciones de un tipo específico
    public double obtenerSaldoTotalPorTipo(TipoTransaccion tipo) {
        Validar.queNoNulo(tipo, "El tipo de transacción no puede ser nulo");

        double total = 0.0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == tipo) {
                total += transaccion.getMonto();
            }
        }
        return total;
    }

    // Metodo para obtener las transacciones realizadas en una fecha específica (si lo deseas)
    // Este es un ejemplo para filtrar por fecha, si se requiere
    public List<Transaccion> obtenerTransaccionesPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Validar.queNoNulo(fechaInicio, "La fecha de inicio no puede ser nula");
        Validar.queNoNulo(fechaFin, "La fecha de fin no puede ser nula");

        List<Transaccion> transaccionesPorFecha = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (!transaccion.getFechaHora().isBefore(fechaInicio) && !transaccion.getFechaHora().isAfter(fechaFin)) {
                transaccionesPorFecha.add(transaccion);
            }
        }
        return transaccionesPorFecha;
    }
}