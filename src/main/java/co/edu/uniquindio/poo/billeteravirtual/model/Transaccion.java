package co.edu.uniquindio.poo.billeteravirtual.model;

import java.time.LocalDateTime;

public class Transaccion {
    private String idTransaccion;
    private LocalDateTime fechaHora;
    private Double monto = 0.0;
    private String descripcion;
    private TipoTransaccion tipo;
    private Categoria categoria;

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

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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