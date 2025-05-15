package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final NotificadorMovimientos notificador;
    private final SaldoService saldoService;
    private final CategoriaService categoriaService;

    public TransaccionService(TransaccionRepository transaccionRepository, NotificadorMovimientos notificador, SaldoService saldoService, CategoriaService categoriaService) {
        this.transaccionRepository = transaccionRepository;
        this.notificador = notificador;
        this.saldoService = saldoService;
        this.categoriaService = categoriaService;
    }

    public Transaccion depositar(String idCuenta, Double monto, String descripcion, String idCategoria) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Transaccion transaccion = new Transaccion(
                GeneradorId.generarId("TRX"),
                LocalDateTime.now(),
                monto,
                descripcion,
                TipoTransaccion.DEPOSITO,
                categoria
        );

        saldoService.actualizarSaldo(idCuenta, monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarDeposito(idCuenta, monto, transaccion);

        return transaccion;
    }

    public Transaccion retirar(String idCuenta, Double monto, String descripcion, String idCategoria) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");

        if (!saldoService.haySaldoSuficiente(idCuenta, monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retiro");
        }

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Transaccion transaccion = new Transaccion(
                GeneradorId.generarId("TRX"),
                LocalDateTime.now(),
                monto,
                descripcion,
                TipoTransaccion.RETIRO,
                categoria
        );

        saldoService.actualizarSaldo(idCuenta, -monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarRetiro(idCuenta, monto, transaccion);

        return transaccion;
    }

    public Transaccion transferir(String cuentaOrigen, String cuentaDestino, Double monto, String descripcion, String idCategoria) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(cuentaOrigen, "La cuenta origen no puede estar vacía");
        Validar.queNoVacio(cuentaDestino, "La cuenta destino no puede estar vacía");

        if (!saldoService.haySaldoSuficiente(cuentaOrigen, monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferencia");
        }

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Transaccion transaccion = new Transaccion(
                GeneradorId.generarId("TRX"),
                LocalDateTime.now(),
                monto,
                descripcion,
                TipoTransaccion.TRANSFERENCIA,
                categoria
        );

        saldoService.actualizarSaldo(cuentaOrigen, -monto);
        saldoService.actualizarSaldo(cuentaDestino, monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarTransferencia(cuentaOrigen, cuentaDestino, monto, transaccion);

        return transaccion;
    }

    public List<Transaccion> obtenerHistorial(String idCuenta, LocalDateTime desde, LocalDateTime hasta) {
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");
        Validar.queNoNulo(desde, "La fecha desde no puede ser nula");
        Validar.queNoNulo(hasta, "La fecha hasta no puede ser nula");

        return transaccionRepository.buscarPorCuentaYFecha(idCuenta, desde, hasta);
    }

    public List<Transaccion> filtrarPorCategoria(String idCuenta, String idCategoria) {
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");
        Validar.queNoVacio(idCategoria, "La categoría no puede estar vacía");

        return transaccionRepository.buscarPorCuentaYCategoria(idCuenta, idCategoria);
    }
}