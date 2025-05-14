package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.dto.TransaccionDTO;
import co.edu.uniquindio.poo.billeteravirtual.model.NotificadorMovimientos;
import co.edu.uniquindio.poo.billeteravirtual.model.SaldoInsuficiente;
import co.edu.uniquindio.poo.billeteravirtual.model.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

import java.time.LocalDateTime;
import java.util.List;

public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final NotificadorMovimientos notificador;
    private final SaldoService saldoService;

    // Inyección de dependencias
    public TransaccionService(TransaccionRepository transaccionRepository, NotificadorMovimientos notificador, SaldoService saldoService) {
        this.transaccionRepository = transaccionRepository;
        this.notificador = notificador;
        this.saldoService = saldoService;
    }

    // Principio de Responsabilidad Única (SRP)
    public Transaccion depositar(String cuentaId, double monto, String descripcion) {
        Validar.quePositivo(monto, "El monto debe ser positivo");

        Transaccion transaccion = new Transaccion(
                TipoTransaccion.DEPOSITO,
                monto,
                LocalDateTime.now(),
                descripcion,
                null,
                cuentaId
        );

        saldoService.actualizarSaldo(cuentaId, monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarDeposito(cuentaId, monto);

        return transaccion;
    }

    // Principio Abierto/Cerrado (OCP)
    public Transaccion retirar(String cuentaId, double monto, String descripcion) {
        Validar.quePositivo(monto, "El monto debe ser positivo");

        if (!saldoService.haySaldoSuficiente(cuentaId, monto)) {
            throw new SaldoInsuficiente("Saldo insuficiente para retiro");
        }

        Transaccion transaccion = new Transaccion(
                TipoTransaccion.RETIRO,
                monto,
                LocalDateTime.now(),
                descripcion,
                cuentaId,
                null
        );

        saldoService.actualizarSaldo(cuentaId, -monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarRetiro(cuentaId, monto);

        return transaccion;
    }

    // Principio de Sustitución de Liskov (LSP)
    public Transaccion transferir(String cuentaOrigen, String cuentaDestino,
                                  double monto, String descripcion) {
        Validar.quePositivo(monto, "El monto debe ser positivo");

        if (!saldoService.haySaldoSuficiente(cuentaOrigen, monto)) {
            throw new SaldoInsuficiente("Saldo insuficiente para transferencia");
        }

        Transaccion transaccion = new Transaccion(
                TipoTransaccion.TRANSFERENCIA,
                monto,
                LocalDateTime.now(),
                descripcion,
                cuentaOrigen,
                cuentaDestino
        );

        saldoService.actualizarSaldo(cuentaOrigen, -monto);
        saldoService.actualizarSaldo(cuentaDestino, monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarTransferencia(cuentaOrigen, cuentaDestino, monto);

        return transaccion;
    }

    // Principio de Segregación de Interfaces (ISP)
    public List<Transaccion> obtenerHistorial(String cuentaId, LocalDateTime desde, LocalDateTime hasta) {
        return transaccionRepository.buscarPorCuentaYFecha(cuentaId, desde, hasta);
    }

    // Principio de Inversión de Dependencias (DIP)
    public interface TransaccionRepository {
        void guardar(Transaccion transaccion);
        List<Transaccion> buscarPorCuentaYFecha(String cuentaId, LocalDateTime desde, LocalDateTime hasta);
    }

    public interface NotificadorMovimientos {
        void notificarDeposito(String cuentaId, double monto);
        void notificarRetiro(String cuentaId, double monto);
        void notificarTransferencia(String cuentaOrigen, String cuentaDestino, double monto);
    }

    public interface SaldoService {
        void actualizarSaldo(String cuentaId, double monto);
        boolean haySaldoSuficiente(String cuentaId, double monto);
    }
}