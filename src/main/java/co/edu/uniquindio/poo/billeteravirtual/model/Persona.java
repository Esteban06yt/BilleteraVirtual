package co.edu.uniquindio.poo.billeteravirtual.model;

public abstract class Persona {
    protected String id;
    protected String cedula;
    protected String nombreCompleto;
    protected String correo;
    protected String telefono;
    protected String contrasenia;
    protected String codigoRecuperacion;

    public Persona(String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("El id no puede estar vacio");
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("La cedula no puede estar vacia");
        if (nombreCompleto == null || nombreCompleto.isBlank()) throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El correo no puede estar vacio");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (contrasenia == null || contrasenia.isBlank()) throw new IllegalArgumentException("La contrasenia no puede estar vacio");
        if (!(contrasenia.length() >= 8 && contrasenia.matches(".*[A-Z].*") && contrasenia.matches(".*\\d.*"))) throw new IllegalArgumentException("La contrasenia no es lo suficientemente segura");

        this.id = id;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo.toLowerCase();
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.codigoRecuperacion = generarCodigoRecuperacion();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", cedula='" + cedula + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", codigoRecuperacion='" + codigoRecuperacion + '\'' +
                '}';
    }

    public String generarCodigoRecuperacion() {
        codigoRecuperacion = CodigoGenerador.generarCodigo();
        return codigoRecuperacion;
    }
}