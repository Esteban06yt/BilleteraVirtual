package co.edu.uniquindio.poo.billeteravirtual.model;

public class Presupuesto implements Cloneable {
    private String idPresupuesto;
    private String nombre;
    private Double montoAsignado;
    private Double montoGastado = 0.0;
    private Categoria categoria;

    public Presupuesto(String nombre, Double montoAsignado, Categoria categoria) {
        this.idPresupuesto = CodigoGenerador.generarId();
        this.nombre = nombre;
        this.montoAsignado = montoAsignado;
        this.montoGastado = 0.0;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public Presupuesto clone() {
        try {
            Presupuesto clon = (Presupuesto) super.clone();
            // Generar nuevo ID para el clon
            return new Presupuesto(this.nombre, this.montoAsignado, this.categoria);
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // No debería ocurrir
        }
    }

    @Override
    public String toString() {
        return "Presupuesto{" +
                "idPresupuesto='" + idPresupuesto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", montoAsignado=" + montoAsignado +
                ", montoGastado=" + montoGastado +
                ", categoria=" + categoria +
                '}';
    }

    public double calcularDisponible() {
        return montoAsignado - montoGastado;
    }
}