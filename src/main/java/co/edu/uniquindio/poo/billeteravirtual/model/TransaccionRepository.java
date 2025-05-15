package co.edu.uniquindio.poo.billeteravirtual.model;

import java.time.LocalDateTime;
import java.util.List;

public interface TransaccionRepository {
    void guardar(Transaccion transaccion);
    List<Transaccion> buscarPorCuentaYFecha(String idCuenta, LocalDateTime desde, LocalDateTime hasta);
    List<Transaccion> buscarPorCuentaYCategoria(String idCuenta, String idCategoria);
    Transaccion buscarPorId(String idTransaccion);
}