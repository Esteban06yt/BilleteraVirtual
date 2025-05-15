package co.edu.uniquindio.poo.billeteravirtual.service;

public interface SaldoService {
    public void actualizarSaldo(String idCuenta, Double monto);
    public boolean haySaldoSuficiente(String idCuenta, Double monto);
    public double obtenerSaldo(String idCuenta);
}