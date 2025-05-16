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
    private final Usuario emisor;
    private final Usuario destinatario;

    // Constructor privado (utilizado solo por el Builder)
    private Transaccion(Builder builder) {
        this.idTransaccion = builder.idTransaccion;
        this.fechaHora = builder.fechaHora;
        this.monto = builder.monto;
        this.descripcion = builder.descripcion;
        this.tipo = builder.tipo;
        this.categoria = builder.categoria;
        this.emisor = builder.emisor;
        this.destinatario = builder.destinatario;
    }

    // Getters
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

    public Usuario getEmisor() {
        return emisor;
    }

    public Usuario getDestinatario() {
        return destinatario;
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
                ", emisor=" + emisor +
                ", destinatario=" + destinatario +
                '}';
    }

    // Builder
    public static class Builder {
        private String idTransaccion;
        private LocalDateTime fechaHora;
        private Double monto;
        private String descripcion;
        private TipoTransaccion tipo;
        private Categoria categoria;
        private Usuario emisor;
        private Usuario destinatario;

        public Builder withIdTransaccion(String idTransaccion) {
            this.idTransaccion = idTransaccion;
            return this;
        }

        public Builder withFechaHora(LocalDateTime fechaHora) {
            this.fechaHora = fechaHora;
            return this;
        }

        public Builder withMonto(Double monto) {
            this.monto = monto;
            return this;
        }

        public Builder withDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder withTipo(TipoTransaccion tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder withCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder withEmisor(Usuario emisor) {
            this.emisor = emisor;
            return this;
        }

        public Builder withDestinatario(Usuario destinatario) {
            this.destinatario = destinatario;
            return this;
        }

        // Metodo para construir la transacción
        public Transaccion build() {
            return new Transaccion(this);
        }
    }
}