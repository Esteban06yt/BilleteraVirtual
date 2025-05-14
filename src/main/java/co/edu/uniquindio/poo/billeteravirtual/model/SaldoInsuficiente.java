package co.edu.uniquindio.poo.billeteravirtual.model;

public class SaldoInsuficiente extends RuntimeException {
    public SaldoInsuficiente(String mensaje) {
        super(mensaje);
    }
}