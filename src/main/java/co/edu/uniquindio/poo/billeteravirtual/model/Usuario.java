package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    private String direccion;
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private List<Presupuesto> presupuestos = new ArrayList<>();

    public Usuario(String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion, String direccion, List<Cuenta> cuentas, List<Transaccion> transacciones, List<Presupuesto> presupuestos) {
        super(id, cedula, nombreCompleto, correo, telefono, contrasenia, codigoRecuperacion);
        this.direccion = direccion;
        this.cuentas = cuentas;
        this.transacciones = transacciones;
        this.presupuestos = presupuestos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getSaldoTotal() {
        Double saldoTotal = 0.0;
        for (Cuenta cuenta : cuentas){
            saldoTotal += cuenta.getMonto();
        }
        return saldoTotal;
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
                ", saldoTotal=" + getSaldoTotal() +
                ", cuentas=" + cuentas +
                ", transacciones=" + transacciones +
                ", presupuestos=" + presupuestos +
                ", id='" + id + '\'' +
                ", cedula='" + cedula + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", codigoRecuperacion='" + codigoRecuperacion + '\'' +
                '}';
    }
}