package co.edu.uniquindio.poo.billeteravirtual.dto;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;

import java.time.LocalDateTime;

public class TransaccionDTO {
    private LocalDateTime fechaHora;
    private TipoTransaccion tipo;
    private Double monto;
    private String descripcion;

    public TransaccionDTO(LocalDateTime fechaHora, TipoTransaccion tipo, Double monto, String descripcion) {
        this.fechaHora = fechaHora;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TransaccionDTO{" +
                "fechaHora=" + fechaHora +
                ", tipo=" + tipo +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}