package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    private Double saldo = getSaldo();
    private String direccion;
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private List<Presupuesto> presupuestos = new ArrayList<>();

    public Usuario(Double saldo, String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion, Boolean esCuentaActiva, String direccion, List<Cuenta> cuentas, List<Transaccion> transacciones, List<Presupuesto> presupuestos) {
        super(id, cedula, nombreCompleto, correo, telefono, contrasenia, codigoRecuperacion, esCuentaActiva);

        Validar.queNoVacio(direccion, "La dirección no puede estar vacía");

        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getSaldo() {
        Double saldo = 0.0;
        for (Cuenta cuenta : cuentas){
            saldo += cuenta.getMonto();
        }
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "direccion='" + direccion + '\'' +
                ", saldoTotal=" + getSaldo() +
                ", cuentas=" + cuentas +
                ", transacciones=" + transacciones +
                ", presupuestos=" + presupuestos +
                ", super='" + super.toString() + '\'' +
                '}';
    }

    public void agregarCuenta(Cuenta cuenta) {
        Validar.queNoNulo(cuenta, "La cuenta no puede ser nula");
        this.cuentas.add(cuenta);
        actualizarSaldoTotal();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        Validar.queNoNulo(transaccion, "La transacción no puede ser nula");
        this.transacciones.add(transaccion);
        actualizarSaldoTotal();
    }

    private void actualizarSaldoTotal() {
        this.saldo = this.cuentas.stream()
                .mapToDouble(Cuenta::getMonto)
                .sum();
    }
}