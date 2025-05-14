package co.edu.uniquindio.poo.billeteravirtual.dto;

import co.edu.uniquindio.poo.billeteravirtual.model.TipoCuenta;

public class CuentaDTO {
    private String nombreBanco;
    private Integer numeroCuenta;
    private TipoCuenta tipo;
    private Double monto = 0.0;

    public CuentaDTO(String nombreBanco, Integer numeroCuenta, TipoCuenta tipo, Double monto) {
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" +
                "nombreBanco='" + nombreBanco + '\'' +
                ", numeroCuenta=" + numeroCuenta +
                ", tipo=" + tipo +
                ", monto=" + monto +
                '}';
    }
}