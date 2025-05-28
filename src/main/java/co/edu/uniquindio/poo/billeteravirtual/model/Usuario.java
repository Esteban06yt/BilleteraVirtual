package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    private Double saldo = 0.0;
    private String direccion;
    private List<Cuenta> cuentas = new ArrayList<>();
    private List<Transaccion> transacciones = new ArrayList<>();
    private List<Presupuesto> presupuestos = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();

    public Usuario(String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String direccion, List<Cuenta> cuentas, List<Transaccion> transacciones, List<Presupuesto> presupuestos, List<Categoria> categorias) {
        super(cedula, nombreCompleto, correo, telefono, contrasenia);
        this.cuentas = (cuentas != null) ? cuentas : new ArrayList<>();
        this.transacciones = (transacciones != null) ? transacciones : new ArrayList<>();
        this.presupuestos = (presupuestos != null) ? presupuestos : new ArrayList<>();
        this.categorias = (categorias !=null)? categorias : new ArrayList<>();

        this.saldo = 0.0;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getSaldo() {
        // Este metodo ahora solo devolverá el saldo de las cuentas, pero no se hará un cálculo sumando todos los saldos.
        return cuentas.stream().mapToDouble(Cuenta::getMonto).sum();
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "direccion='" + direccion + '\'' +
                ", saldoTotal=" + getSaldo() +
                ", cuentas=" + cuentas +
                ", transacciones=" + transacciones +
                ", presupuestos=" + presupuestos +
                ", categorias=" + categorias +
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

    public void restarDeCuenta(Double monto, Cuenta cuentaSeleccionada) {
        // Este metodo se encarga de restar el dinero de la cuenta seleccionada.
        if (cuentaSeleccionada.getMonto() >= monto) {
            cuentaSeleccionada.setMonto(cuentaSeleccionada.getMonto() - monto);
        } else {
            throw new IllegalArgumentException("No hay suficiente saldo en la cuenta seleccionada.");
        }
    }

    public boolean puedeModificarCuenta(Cuenta cuenta) {
        return this.getCuentas().stream()
                .anyMatch(c -> c.getIdCuenta().equals(cuenta.getIdCuenta()));
    }
}