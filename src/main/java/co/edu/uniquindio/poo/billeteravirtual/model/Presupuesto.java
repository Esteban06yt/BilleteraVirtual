package co.edu.uniquindio.poo.billeteravirtual.model;

public class Presupuesto {
    private String idPresupuesto;
    private String nombre;
    private Double montoAsignado = 0.0;
    private Double montoGastado = 0.0;

    public Presupuesto(String idPresupuesto, String nombre, Double montoAsignado, Double montoGastado) {
        this.idPresupuesto = idPresupuesto;
        this.nombre = nombre;
        this.montoAsignado = montoAsignado;
        this.montoGastado = montoGastado;
    }

    public String getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(String idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(Double montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public Double getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(Double montoGastado) {
        this.montoGastado = montoGastado;
    }

    @Override
    public String toString() {
        return "Presupuesto{" +
                "idPresupuesto='" + idPresupuesto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", montoAsignado=" + montoAsignado +
                ", montoGastado=" + montoGastado +
                ", montoRestante=" + calcularDisponible() +
                '}';
    }

    public double calcularDisponible() {
        return montoAsignado - montoGastado;
    }
}