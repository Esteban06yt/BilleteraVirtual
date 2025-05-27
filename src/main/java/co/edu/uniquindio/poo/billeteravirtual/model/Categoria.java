package co.edu.uniquindio.poo.billeteravirtual.model;

public class Categoria {
    private String idCategoria;
    private String nombre;
    private String descripcion;

    public Categoria(String nombre, String descripcion) {
        this.idCategoria = CodigoGenerador.generarId();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria='" + idCategoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}