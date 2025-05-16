package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;

public class Cuenta {
    private String idCuenta;
    private String nombreBanco;
    private Integer numeroCuenta;
    private Double monto = 0.0;
    private TipoCuenta tipo;

    public Cuenta(String idCuenta, String nombreBanco, Integer numeroCuenta, Double monto, TipoCuenta tipo) {
        this.idCuenta = CodigoGenerador.generarId();
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = CodigoGenerador.generarIdCuenta();
        this.tipo = tipo;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
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

    // Métodos de negocio
    public void transferir(double monto, Cuenta destino) {
        if(this.monto >= monto) {
            this.monto -= monto;
            destino.monto += monto;
        }
    }
}