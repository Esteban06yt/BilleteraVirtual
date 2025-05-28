package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;
import co.edu.uniquindio.poo.billeteravirtual.interfaces.CuentaInterface;

public class Cuenta implements CuentaInterface {
    private final String idCuenta;
    private final String nombreBanco;
    private final Integer numeroCuenta;
    private Double monto = 0.0;
    private TipoCuenta tipo;

    public Cuenta(String nombreBanco, TipoCuenta tipo, String idCuenta, Integer numeroCuenta, Double monto) {
        this.nombreBanco = nombreBanco;
        this.tipo = tipo;

        // Si quieres usar los valores que vienen como parámetros:
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;

        // Si quieres generar nuevos valores usando CodigoGenerador (ignorando los parámetros):
        // this.idCuenta = CodigoGenerador.generarId();
        // this.numeroCuenta = CodigoGenerador.generarIdCuenta();

        this.monto = monto;
    }

    public Cuenta(String nombreBanco, TipoCuenta tipo) {
        this.nombreBanco = nombreBanco;
        this.tipo = tipo;
        this.idCuenta = CodigoGenerador.generarId();
        this.numeroCuenta = CodigoGenerador.generarIdCuenta();
        this.monto = 0.0;
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