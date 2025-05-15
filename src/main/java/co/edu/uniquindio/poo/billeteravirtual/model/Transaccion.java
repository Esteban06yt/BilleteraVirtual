package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;

import java.time.LocalDateTime;

public class Transaccion {
    private final String idTransaccion;
    private final LocalDateTime fechaHora;
    private final Double monto;
    private final String descripcion;
    private final TipoTransaccion tipo;
    private final Categoria categoria;

    public Transaccion(String idTransaccion, LocalDateTime fechaHora, Double monto, String descripcion, TipoTransaccion tipo, Categoria categoria) {
        this.idTransaccion = idTransaccion;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "idTransaccion='" + idTransaccion + '\'' +
                ", fechaHora=" + fechaHora +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", categoria=" + categoria +
                '}';
    }
}