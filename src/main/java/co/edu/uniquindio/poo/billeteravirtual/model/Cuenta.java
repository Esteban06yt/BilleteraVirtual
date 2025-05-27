package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;
import co.edu.uniquindio.poo.billeteravirtual.interfaces.CuentaInterface;

public class Cuenta implements CuentaInterface {
    private final String idCuenta;
    private final String nombreBanco;
    private final Integer numeroCuenta;
    private Double monto = 0.0;
    private TipoCuenta tipo;

    public Cuenta(String nombreBanco, TipoCuenta tipo) {
        this.idCuenta = CodigoGenerador.generarId();
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = CodigoGenerador.generarIdCuenta();
        this.tipo = tipo;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta='" + idCuenta + '\'' +
                ", nombreBanco='" + nombreBanco + '\'' +
                ", numeroCuenta=" + numeroCuenta +
                ", monto=" + monto +
                ", tipo=" + tipo +
                '}';
    }

    @Override
    public void transferir(double monto, CuentaInterface destino) {
        if (this.monto >= monto) {
            this.monto -= monto;
            destino.setMonto(destino.getMonto() + monto);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}