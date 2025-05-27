package co.edu.uniquindio.poo.billeteravirtual.facade;

import co.edu.uniquindio.poo.billeteravirtual.enums.*;
import co.edu.uniquindio.poo.billeteravirtual.model.*;

import java.util.List;

public class SistemaBilleteraFacade {

    private final BilleteraVirtual sistema = BilleteraVirtual.getInstance();

    // -------------------------
    // Usuarios
    // -------------------------
    public void registrarUsuario(Usuario usuario) {
        sistema.getGestorUsuarios().agregarUsuario(usuario);
    }

    public Usuario buscarUsuario(String cedula) {
        return sistema.getGestorUsuarios().buscarUsuario(cedula);
    }

    public List<Usuario> listarUsuarios() {
        return sistema.getGestorUsuarios().getUsuarios();
    }

    public boolean actualizarUsuario(String cedula, String nombre, String correo, String telefono, String contrasenia, String direccion) {
        return sistema.getGestorUsuarios()
                .actualizarUsuario(cedula, nombre, correo, telefono, contrasenia, direccion);
    }

    public boolean eliminarUsuario(String cedula) {
        return sistema.getGestorUsuarios().eliminarUsuario(cedula);
    }

    // -------------------------
    // Administradores
    // -------------------------
    public void registrarAdministrador(Administrador admin) {
        sistema.getGestorAdministradores().agregarAdministrador(admin);
    }

    public Administrador buscarAdministrador(String cedula) {
        return sistema.getGestorAdministradores().buscarAdministrador(cedula);
    }

    public List<Administrador> listarAdministradores() {
        return sistema.getGestorAdministradores().getAdministradores();
    }

    public boolean actualizarAdministrador(String cedula, String nombre, String correo, String telefono, String contrasenia, RolAdministrador rol) {
        return sistema.getGestorAdministradores().actualizarAdministrador(cedula, nombre, correo, telefono, contrasenia, rol);
    }

    public boolean eliminarAdministrador(String cedula) {
        return sistema.getGestorAdministradores().eliminarAdministrador(cedula);
    }

    // -------------------------
    // Cuentas
    // -------------------------
    public void agregarCuenta(Cuenta cuenta) {
        sistema.getGestorCuentas().agregarCuenta(cuenta);
    }

    public void eliminarCuenta(String idCuenta) {
        sistema.getGestorCuentas().eliminarCuenta(idCuenta);
    }

    public Cuenta buscarCuenta(String idCuenta) {
        return sistema.getGestorCuentas().buscarCuentaPorId(idCuenta);
    }

    public List<Cuenta> listarCuentas() {
        return sistema.getGestorCuentas().obtenerCuentas();
    }

    public List<Cuenta> listarCuentasDeUsuario(Usuario usuario) {
        return sistema.getGestorCuentas().obtenerCuentasDeUsuario(usuario);
    }

    public void transferir(String idOrigen, String idDestino, double monto) {
        sistema.getGestorCuentas().realizarTransferencia(idOrigen, idDestino, monto);
    }

    public void pago(Usuario usuario, Double monto, String idCuenta, String servicio) {
        sistema.getGestorCuentas().realizarPago(usuario, monto, idCuenta, servicio);
    }

    // -------------------------
    // Transacciones
    // -------------------------
    public void agregarTransaccion(String idTransaccion, Double monto, String descripcion, TipoTransaccion tipo, Categoria categoria, Usuario emisor, Usuario destinatario, String idCuentaEmisor) {
        sistema.getGestorTransacciones()
                .agregarTransaccion(idTransaccion, monto, descripcion, tipo,
                        categoria, emisor, destinatario, idCuentaEmisor);
    }

    public List<Transaccion> listarTransacciones() {
        return sistema.getGestorTransacciones().listarTransacciones();
    }

    public boolean eliminarTransaccion(String idTransaccion) {
        try {
            sistema.getGestorTransacciones().eliminarTransaccion(idTransaccion);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Transaccion> listarTransaccionesDeUsuario(Usuario usuario) {
        return sistema.getGestorTransacciones().obtenerTransaccionesPorUsuario(usuario);
    }

    public List<Transaccion> listarTransaccionesPorTipo(TipoTransaccion tipo) {
        return sistema.getGestorTransacciones().obtenerTransaccionesPorTipo(tipo);
    }

    // -------------------------
    // Presupuestos
    // -------------------------

    public void agregarPresupuesto(Presupuesto presupuesto, Usuario usuarioActual) {
        Validar.queNoNulo(usuarioActual, "No hay un usuario activo");
        sistema.getGestorPresupuestos().agregarPresupuestoAUsuario(usuarioActual, presupuesto);
    }

    public boolean eliminarPresupuesto(String idPresupuesto, Usuario usuarioActual) {
        Validar.queNoNulo(usuarioActual, "No hay un usuario activo");
        try {
            sistema.getGestorPresupuestos().eliminarPresupuestoDeUsuario(usuarioActual, idPresupuesto);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Presupuesto> listarPresupuestos(Usuario usuarioActual) {
        Validar.queNoNulo(usuarioActual, "No hay un usuario activo");
        return sistema.getGestorPresupuestos().obtenerPresupuestosDeUsuario(usuarioActual);
    }

    // -------------------------
    // Categorías
    // -------------------------
    public void agregarCategoria(Categoria categoria) {
        sistema.getGestorCategorias().agregarCategoria(categoria);
    }

    public boolean eliminarCategoria(String idCategoria) {
        try {
            sistema.getGestorCategorias().eliminarCategoria(idCategoria);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Categoria> listarCategorias() {
        return sistema.getGestorCategorias().listarCategorias();
    }

    public Categoria buscarCategoriaPorNombre(String nombre) {
        return sistema.getGestorCategorias().buscarCategoriaPorNombre(nombre);
    }

    // -------------------------
    // Estadísticas
    // -------------------------
    public double saldoPromedioUsuarios() {
        return sistema.getGestorEstadisticas().saldoPromedioUsuarios();
    }

    public List<Usuario> topUsuariosPorTransacciones(int topN) {
        return sistema.getGestorEstadisticas().usuariosConMasTransacciones(topN);
    }

    public java.util.Map<Categoria, Double> gastosPorCategoria(Usuario usuario) {
        return sistema.getGestorEstadisticas().obtenerGastosPorCategoria(usuario);
    }

    // -------------------------
    // Reportes
    // -------------------------
    public void exportarCSVTransacciones(List<Transaccion> transacciones, String ruta) throws java.io.IOException {
        sistema.getGestorReportes().exportarTransaccionesACSV(transacciones, ruta);
    }

    public void exportarPDFTransacciones(List<Transaccion> transacciones, String ruta) throws java.io.IOException {
        sistema.getGestorReportes().exportarTransaccionesAPDF(transacciones, ruta);
    }
}