package co.edu.uniquindio.poo.billeteravirtual.interfaces;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;

public interface CuentaInterface {
    String getIdCuenta();
    String getNombreBanco();
    Integer getNumeroCuenta();
    Double getMonto();
    TipoCuenta getTipo();
    void setMonto(Double monto);
    void setTipo(TipoCuenta tipo);
    void transferir(double monto, CuentaInterface destino);
}