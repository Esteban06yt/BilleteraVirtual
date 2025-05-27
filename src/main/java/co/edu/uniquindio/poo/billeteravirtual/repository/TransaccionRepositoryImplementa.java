package co.edu.uniquindio.poo.billeteravirtual.repository;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.TransaccionRepository;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.Validar;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransaccionRepositoryImplementa implements TransaccionRepository {
    private final Map<String, Transaccion> transacciones = new HashMap<>();
    private final Map<String, List<String>> transaccionesPorCuenta = new HashMap<>();
    private final Map<String, List<String>> transaccionesPorCategoria = new HashMap<>();

    @Override
    public void guardar(Transaccion transaccion) {
        Validar.queNoNulo(transaccion, "La transacción no puede ser nula");
        Validar.queNoVacio(transaccion.getIdTransaccion(), "El ID de transacción no puede estar vacío");

        transacciones.put(transaccion.getIdTransaccion(), transaccion);

        // Indexar por cuenta
        String idCuenta = obtenerIdCuentaDeTransaccion(transaccion);
        transaccionesPorCuenta.computeIfAbsent(idCuenta, k -> new ArrayList<>())
                .add(transaccion.getIdTransaccion());

        // Indexar por categoría
        if (transaccion.getCategoria() != null) {
            transaccionesPorCategoria.computeIfAbsent(transaccion.getCategoria().getIdCategoria(), k -> new ArrayList<>())
                    .add(transaccion.getIdTransaccion());
        }
    }

    @Override
    public List<Transaccion> buscarPorCuentaYFecha(String idCuenta, LocalDateTime desde, LocalDateTime hasta) {
        Validar.queNoVacio(idCuenta, "El ID de cuenta no puede estar vacío");
        Validar.queNoNulo(desde, "La fecha desde no puede ser nula");
        Validar.queNoNulo(hasta, "La fecha hasta no puede ser nula");

        return Optional.ofNullable(transaccionesPorCuenta.get(idCuenta))
                .orElseGet(ArrayList::new)
                .stream()
                .map(transacciones::get)
                .filter(t -> t != null && !t.getFechaHora().isBefore(desde) && !t.getFechaHora().isAfter(hasta))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> buscarPorCuentaYCategoria(String idCuenta, String idCategoria) {
        Validar.queNoVacio(idCuenta, "El ID de cuenta no puede estar vacío");
        Validar.queNoVacio(idCategoria, "El ID de categoría no puede estar vacío");

        List<String> idsTransaccionesCategoria = Optional.ofNullable(transaccionesPorCategoria.get(idCategoria))
                .orElseGet(ArrayList::new);

        return idsTransaccionesCategoria.stream()
                .map(transacciones::get)
                .filter(t -> t != null && perteneceACuenta(t, idCuenta))
                .collect(Collectors.toList());
    }

    @Override
    public Transaccion buscarPorId(String idTransaccion) {
        Validar.queNoVacio(idTransaccion, "El ID de transacción no puede estar vacío");
        return transacciones.get(idTransaccion);
    }

    @Override
    public boolean existeIdTransaccion(String idTransaccion) {
        Validar.queNoVacio(idTransaccion, "El ID de transacción no puede estar vacío");
        return transacciones.containsKey(idTransaccion);
    }

    private String obtenerIdCuentaDeTransaccion(Transaccion transaccion) {
        if (transaccion.getEmisor() != null) {
            return transaccion.getEmisor().getId();
        }
        throw new IllegalArgumentException("Transacción no asociada a una cuenta válida");
    }

    private boolean perteneceACuenta(Transaccion transaccion, String idCuenta) {
        String transaccionIdCuenta = obtenerIdCuentaDeTransaccion(transaccion);
        return idCuenta.equals(transaccionIdCuenta);
    }
}