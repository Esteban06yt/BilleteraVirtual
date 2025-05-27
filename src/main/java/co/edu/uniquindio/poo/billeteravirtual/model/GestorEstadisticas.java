package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;

import java.util.*;
import java.util.stream.Collectors;

public class GestorEstadisticas {

    private final List<Usuario> usuarios;
    private final List<Transaccion> transacciones;

    public GestorEstadisticas(List<Usuario> usuarios, List<Transaccion> transacciones) {
        this.usuarios = usuarios;
        this.transacciones = transacciones;
    }

    public Map<Categoria, Double> obtenerGastosPorCategoria(Usuario usuario) {
        return transacciones.stream()
                .filter(t -> t.getEmisor().equals(usuario))
                .filter(t -> t.getTipo() == TipoTransaccion.PAGO || t.getTipo() == TipoTransaccion.TRANSFERENCIA)
                .collect(Collectors.groupingBy(
                        Transaccion::getCategoria,
                        Collectors.summingDouble(Transaccion::getMonto)
                ));
    }

    public List<Usuario> usuariosConMasTransacciones(int topN) {
        Map<Usuario, Long> conteo = transacciones.stream()
                .collect(Collectors.groupingBy(Transaccion::getEmisor, Collectors.counting()));
        return conteo.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .limit(topN)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public double saldoPromedioUsuarios() {
        if (usuarios.isEmpty()) return 0.0;
        double suma = usuarios.stream()
                .mapToDouble(Usuario::getSaldo)
                .sum();
        return suma / usuarios.size();
    }
}