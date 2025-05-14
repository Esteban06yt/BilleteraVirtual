package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;

public class Administrador extends Persona {
    private RolAdministrador rol;

    public Administrador(String id, String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String codigoRecuperacion, RolAdministrador rol) {
        super(id, cedula, nombreCompleto, correo, telefono, contrasenia, codigoRecuperacion);

        if (id == null || id.isBlank()) throw new IllegalArgumentException("El id no puede estar vacio");
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("La cedula no puede estar vacia");
        if (nombreCompleto == null || nombreCompleto.isBlank()) throw new IllegalArgumentException("El nombre completo no puede estar vacio");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El correo no puede estar vacio");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El telefono no puede estar vacio");
        if (contrasenia == null || contrasenia.isBlank()) throw new IllegalArgumentException("La contrasenia no puede estar vacio");
        if (!(contrasenia.length() >= 8 && contrasenia.matches(".*[A-Z].*") && contrasenia.matches(".*\\d.*"))) throw new IllegalArgumentException("La contrasenia no es lo suficientemente segura");
        if (rol == null) throw new IllegalArgumentException("El rol no puede estar vacio");

        this.rol = rol;
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
                "rol=" + rol +
                ", id='" + id + '\'' +
                ", cedula='" + cedula + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", codigoRecuperacion='" + codigoRecuperacion + '\'' +
                '}';
    }

    public boolean tienePermiso(Permiso permiso) {
        return this.rol.tienePermiso(permiso);
    }
}