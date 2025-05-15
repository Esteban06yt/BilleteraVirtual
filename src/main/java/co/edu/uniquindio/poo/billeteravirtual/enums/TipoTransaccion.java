package co.edu.uniquindio.poo.billeteravirtual.enums;

public enum TipoTransaccion {
    DEPOSITO("Depósito"),
    RETIRO("Retiro"),
    TRANSFERENCIA("Transferencia"),
    PAGO("Pago de servicio"),
    RECARGA("Recarga"),
    PRESTAMO("Prestamo");

    private final String descripcion;

    TipoTransaccion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}