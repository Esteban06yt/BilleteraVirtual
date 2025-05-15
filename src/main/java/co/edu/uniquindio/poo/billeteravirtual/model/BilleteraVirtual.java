package co.edu.uniquindio.poo.billeteravirtual.model;

import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;

import java.util.ArrayList;
import java.util.List;

public class BilleteraVirtual {
    private static BilleteraVirtual instancia;
    private List<Persona> personas;
    private List<Usuario> usuarios;
    private List<Administrador> administradores;

    private BilleteraVirtual() {
        this.personas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }

    public static BilleteraVirtual getInsancia(){
        if (instancia == null){
            instancia = new BilleteraVirtual();
        }
        return instancia;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    public Usuario buscarUsuario(String cedula){
        for (Usuario u : usuarios){
            if (u.getCedula().equalsIgnoreCase(cedula))
                return u;
        }
        return null;
    }

    public boolean actualizarUsuario(String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, String direccion){
        Usuario u = buscarUsuario(cedula);
        if (u != null){
            u.setCedula(cedula);
            u.setNombreCompleto(nombreCompleto);
            u.setCorreo(correo);
            u.setTelefono(telefono);
            u.setContrasenia(contrasenia);
            u.setDireccion(direccion);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(String cedula){
        Usuario u = buscarUsuario(cedula);
        if (u != null){
            usuarios.remove(u);
            return true;
        }
        return false;
    }

    public void agregarAdministrador(Administrador administrador){
        administradores.add(administrador);
    }

    public List<Administrador> listarAdministradores(){
        return administradores;
    }

    public Administrador buscarAdministrador(String cedula){
        for (Administrador a : administradores){
            if (a.getCedula().equalsIgnoreCase(cedula))
                return a;
        }
        return null;
    }

    public boolean actualizarAdministrador(String cedula, String nombreCompleto, String correo, String telefono, String contrasenia, RolAdministrador rol){
        Administrador a = buscarAdministrador(cedula);
        if (a != null){
            a.setCedula(cedula);
            a.setNombreCompleto(nombreCompleto);
            a.setCorreo(correo);
            a.setTelefono(telefono);
            a.setContrasenia(contrasenia);
            a.setRol(rol);
            return true;
        }
        return false;
    }

    public boolean eliminarAdministrador(String cedula){
        Administrador a = buscarAdministrador(cedula);
        if (a != null){
            administradores.remove(a);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BilleteraVirtual{" +
                "personas=" + personas +
                ", usuarios=" + usuarios +
                ", administradores=" + administradores +
                '}';
    }

    public Persona autenticar(String correo, String contrasenia){
        for (Usuario u : usuarios){
            if (u.getCorreo().equalsIgnoreCase(correo) && u.getContrasenia().equalsIgnoreCase(contrasenia)){
                return u;
            }
        }
        for (Administrador a : administradores) {
            if (a.getCorreo().equalsIgnoreCase(correo) && a.getContrasenia().equalsIgnoreCase(contrasenia)) {
                return a;
            }
        }
        return null;
    }
}