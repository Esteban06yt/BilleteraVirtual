package co.edu.uniquindio.poo.billeteravirtual.model;

public abstract class Persona {
    protected String id;
    protected String cedula;
    protected String nombreCompleto;
    protected String correo;
    protected String telefono;
    protected String contrasenia;
    protected String codigoRecuperacion;
    protected Boolean esCuentaActiva;

    public Persona(String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion, Boolean esCuentaActiva) {
        Validar.queNoVacio(id, "El id no puede estar vacío");
        Validar.queCedulaValida(cedula);
        Validar.queNoVacio(nombreCompleto, "El nombre completo no puede estar vacío");
        Validar.queCorreoValido(correo);
        Validar.queTelefonoValido(telefono);
        Validar.queContraseniaSegura(contrasenia);

        this.id = id;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo.toLowerCase();
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.codigoRecuperacion = generarCodigoRecuperacion();
        this.esCuentaActiva = true;
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
        this.codigoRecuperacion = CodigoGenerador.generarCodigo();
    }

    public Boolean getEsCuentaActiva() {
        return esCuentaActiva;
    }
    public void setEsCuentaActiva(Boolean esCuentaActiva) {
        this.esCuentaActiva = esCuentaActiva;
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
                ", esCuentaActiva=" + esCuentaActiva +
                '}';
    }

    public String generarCodigoRecuperacion() {
        codigoRecuperacion = CodigoGenerador.generarCodigo();
        return codigoRecuperacion;
    }
}