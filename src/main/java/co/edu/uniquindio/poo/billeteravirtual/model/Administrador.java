package co.edu.uniquindio.poo.billeteravirtual.model;

public class Administrador extends Persona {
    private String tokenAutenticacion;
    private RolAdministrador rol;

    public Administrador(String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion, String tokenAutenticacion, RolAdministrador rol) {
        super(id, cedula, nombreCompleto, correo, telefono, contrasenia, codigoRecuperacion);
        this.tokenAutenticacion = tokenAutenticacion;
        this.rol = rol;
    }

    public String getTokenAutenticacion() {
        return tokenAutenticacion;
    }

    public void setTokenAutenticacion(String tokenAutenticacion) {
        this.tokenAutenticacion = tokenAutenticacion;
    }

    public RolAdministrador getRol() {
        return rol;
    }

    public void setRol(RolAdministrador rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "tokenAutenticacion='" + tokenAutenticacion + '\'' +
                ", rol=" + rol +
                ", id='" + id + '\'' +
                ", cedula='" + cedula + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", codigoRecuperacion='" + codigoRecuperacion + '\'' +
                '}';
    }

    public boolean autenticarDosFactores(String contrasenia, String codigo2FA) {
        return this.contrasenia.equals(contrasenia) && validarToken2FA(this.tokenAutenticacion, codigo2FA);
    }

    public boolean tienePermiso(Permiso permiso) {
        return this.rol.tienePermiso(permiso);
    }
}