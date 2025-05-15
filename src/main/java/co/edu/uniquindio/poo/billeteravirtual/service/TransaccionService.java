package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.exception.SaldoInsuficienteException;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import co.edu.uniquindio.poo.billeteravirtual.repository.TransaccionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final NotificadorMovimientos notificador;
    private final SaldoService saldoService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public TransaccionService(TransaccionRepository transaccionRepository, NotificadorMovimientos notificador, SaldoService saldoService, CategoriaService categoriaService, UsuarioService usuarioService) {
        this.transaccionRepository = transaccionRepository;
        this.notificador = notificador;
        this.saldoService = saldoService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    public Transaccion depositar(String idCuenta, Double monto, String descripcion, String idCategoria, String idUsuario) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");
        Validar.queNoVacio(idUsuario, "El ID de usuario no puede estar vacío");

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

        Transaccion transaccion = new Transaccion(
                CodigoGenerador.generarId("DEP"),
                LocalDateTime.now(),
                monto,
                descripcion,
                TipoTransaccion.DEPOSITO,
                categoria,
                usuario,    // Emisor (en depósitos puede ser el mismo usuario)
                usuario     // Destinatario
        );

        saldoService.actualizarSaldo(idCuenta, monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarDeposito(usuario, monto, transaccion);

        return transaccion;
    }

    public Transaccion retirar(String idCuenta, Double monto, String descripcion, String idCategoria, String idUsuario) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");
        Validar.queNoVacio(idUsuario, "El ID de usuario no puede estar vacío");

        if (!saldoService.haySaldoSuficiente(idCuenta, monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para retiro");
        }

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

        Transaccion transaccion = new Transaccion(
                CodigoGenerador.generarId("RET"),
                LocalDateTime.now(),
                monto,
                descripcion,
                TipoTransaccion.RETIRO,
                categoria,
                usuario,    // Emisor
                null        // En retiros no hay destinatario específico
        );

        saldoService.actualizarSaldo(idCuenta, -monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarRetiro(usuario, monto, transaccion);

        return transaccion;
    }

    public Transaccion transferir(String idCuentaOrigen, String idCuentaDestino, Double monto,
                                  String descripcion, String idCategoria, String idUsuarioEmisor) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuentaOrigen, "La cuenta origen no puede estar vacía");
        Validar.queNoVacio(idCuentaDestino, "La cuenta destino no puede estar vacía");
        Validar.queNoVacio(idUsuarioEmisor, "El ID de usuario emisor no puede estar vacío");

        if (!saldoService.haySaldoSuficiente(idCuentaOrigen, monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferencia");
        }

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Usuario emisor = usuarioService.obtenerUsuario(idUsuarioEmisor);
        Usuario destinatario = usuarioService.obtenerUsuarioPorCuenta(idCuentaDestino);

        Transaccion transaccion = new Transaccion(
                CodigoGenerador.generarId("TRA"),
                LocalDateTime.now(),
                monto,
                descripcion,
                TipoTransaccion.TRANSFERENCIA,
                categoria,
                emisor,
                destinatario
        );

        saldoService.actualizarSaldo(idCuentaOrigen, -monto);
        saldoService.actualizarSaldo(idCuentaDestino, monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarTransferencia(emisor, destinatario, monto, transaccion);

        return transaccion;
    }

    public Transaccion pagarServicio(String idCuenta, Double monto, String descripcion,
                                     String idCategoria, String idUsuario, String beneficiario) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");
        Validar.queNoVacio(idUsuario, "El ID de usuario no puede estar vacío");
        Validar.queNoVacio(beneficiario, "El beneficiario no puede estar vacío");

        if (!saldoService.haySaldoSuficiente(idCuenta, monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para pago de servicio");
        }

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

        Transaccion transaccion = new Transaccion(
                CodigoGenerador.generarId("PAG"),
                LocalDateTime.now(),
                monto,
                descripcion + " - Beneficiario: " + beneficiario,
                TipoTransaccion.PAGO,
                categoria,
                usuario,
                null  // El beneficiario es externo al sistema
        );

        saldoService.actualizarSaldo(idCuenta, -monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarPago(usuario, monto, beneficiario, transaccion);

        return transaccion;
    }

    public Transaccion recargar(String idCuenta, Double monto, String descripcion,
                                String idCategoria, String idUsuario, String operador) {
        Validar.quePositivo(monto, "El monto debe ser positivo");
        Validar.queNoVacio(idCuenta, "La cuenta no puede estar vacía");
        Validar.queNoVacio(idUsuario, "El ID de usuario no puede estar vacío");
        Validar.queNoVacio(operador, "El operador no puede estar vacío");

        if (!saldoService.haySaldoSuficiente(idCuenta, monto)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para recarga");
        }

        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

        Transaccion transaccion = new Transaccion(
                CodigoGenerador.generarId("REC"),
                LocalDateTime.now(),
                monto,
                descripcion + " - Operador: " + operador,
                TipoTransaccion.RECARGA,
                categoria,
                usuario,
                null  // El operador es externo al sistema
        );

        saldoService.actualizarSaldo(idCuenta, -monto);
        transaccionRepository.guardar(transaccion);
        notificador.notificarRecarga(usuario, monto, operador, transaccion);

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

    public List<Transaccion> obtenerTransaccionesPorUsuario(String idUsuario) {
        Validar.queNoVacio(idUsuario, "El ID de usuario no puede estar vacío");
        return transaccionRepository.buscarPorUsuario(idUsuario);
    }
}