package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    private String direccion;
    private List<Cuenta> cuentas;
    private List<Transaccion> transacciones;
    private List<Presupuesto> presupuestos;

    public Usuario(String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion, String direccion, List<Cuenta> cuentas, List<Transaccion> transacciones, List<Presupuesto> presupuestos) {
        super(id, cedula, nombreCompleto, correo, telefono, contrasenia, codigoRecuperacion);

        if (id == null || id.isBlank()) throw new IllegalArgumentException("El id no puede estar vacio");
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("La cedula no puede estar vacia");
        if (nombreCompleto == null || nombreCompleto.isBlank()) throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El correo no puede estar vacio");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (contrasenia == null || contrasenia.isBlank()) throw new IllegalArgumentException("La contrasenia no puede estar vacio");
        if (!(contrasenia.length() >= 8 && contrasenia.matches(".*[A-Z].*") && contrasenia.matches(".*\\d.*"))) throw new IllegalArgumentException("La contrasenia no es lo suficientemente segura");
        if (direccion == null || direccion.isBlank()) throw new IllegalArgumentException("La direccion no puede estar vacia");

        this.direccion = direccion;
        this.cuentas = new ArrayList<>();
        this.transacciones = new ArrayList<>();
        this.presupuestos = new ArrayList<>();
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