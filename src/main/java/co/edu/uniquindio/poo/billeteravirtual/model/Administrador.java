package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.Permiso;
import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;

public class Administrador extends Persona {
    private RolAdministrador rol;

    public Administrador(String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, RolAdministrador rol) {
        super(cedula, nombreCompleto, correo, telefono, contrasenia);

        Validar.queNoNulo(rol, "El rol no puede ser nulo");

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
                ", super='" + super.toString() +
                '}';
    }

    public boolean tienePermiso(Permiso permiso) {
        return this.rol.tienePermiso(permiso);
    }
}