package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase gestora para operaciones sobre usuarios.
 */
public class GestorUsuarios {
    private final List<Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Agrega un usuario si su cédula no existe aún.
     * @throws IllegalArgumentException si la cédula ya está registrada.
     */
    public void agregarUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getCedula()) != null) {
            throw new IllegalArgumentException("La cédula ya existe: " + usuario.getCedula());
        }
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String cedula) {
        for (Usuario u : usuarios) {
            if (u.getCedula().equalsIgnoreCase(cedula)) {
                return u;
            }
        }
        return null;
    }

    public boolean actualizarUsuario(String cedula, String nombreCompleto, String correo,
                                     String telefono, String contrasenia, String direccion) {
        Usuario u = buscarUsuario(cedula);
        if (u != null) {
            u.setNombreCompleto(nombreCompleto);
            u.setCorreo(correo);
            u.setTelefono(telefono);
            u.setContrasenia(contrasenia);
            u.setDireccion(direccion);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(String cedula) {
        Usuario u = buscarUsuario(cedula);
        if (u != null) {
            usuarios.remove(u);
            return true;
        }
        return false;
    }

    /**
     * Auténtica un usuario por correo y contraseña.
     * @return el Usuario si las credenciales son válidas, o null en caso contrario.
     */
    public Usuario autenticarUsuario(String correo, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)
                    && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }
}