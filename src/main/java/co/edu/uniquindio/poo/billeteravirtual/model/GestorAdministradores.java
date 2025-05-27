package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase gestora para operaciones sobre administradores.
 */
public class GestorAdministradores {
    private final List<Administrador> administradores;

    public GestorAdministradores() {
        this.administradores = new ArrayList<>();
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    /**
     * Agrega un administrador si su cédula no existe aún.
     * @throws IllegalArgumentException si la cédula ya está registrada.
     */
    public void agregarAdministrador(Administrador administrador) {
        if (buscarAdministrador(administrador.getCedula()) != null) {
            throw new IllegalArgumentException("La cédula ya existe: " + administrador.getCedula());
        }
        administradores.add(administrador);
    }

    public Administrador buscarAdministrador(String cedula) {
        for (Administrador a : administradores) {
            if (a.getCedula().equalsIgnoreCase(cedula)) {
                return a;
            }
        }
        return null;
    }

    public boolean actualizarAdministrador(String cedula, String nombreCompleto, String correo,
                                           String telefono, String contrasenia, RolAdministrador rol) {
        Administrador a = buscarAdministrador(cedula);
        if (a != null) {
            a.setNombreCompleto(nombreCompleto);
            a.setCorreo(correo);
            a.setTelefono(telefono);
            a.setContrasenia(contrasenia);
            a.setRol(rol);
            return true;
        }
        return false;
    }

    public boolean eliminarAdministrador(String cedula) {
        Administrador a = buscarAdministrador(cedula);
        if (a != null) {
            administradores.remove(a);
            return true;
        }
        return false;
    }

    /**
     * Auténtica un administrador por correo y contraseña.
     * @return el Administrador si las credenciales son válidas, o null en caso contrario.
     */
    public Administrador autenticarAdministrador(String correo, String contrasenia) {
        for (Administrador a : administradores) {
            if (a.getCorreo().equalsIgnoreCase(correo)
                    && a.getContrasenia().equals(contrasenia)) {
                return a;
            }
        }
        return null;
    }
}