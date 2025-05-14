package co.edu.uniquindio.poo.billeteravirtual.model;

import java.util.ArrayList;
import java.util.List;

public class BilleteraVirtual {
    private List<Persona> personas = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Administrador> administradores = new ArrayList<>();

    public BilleteraVirtual(List<Persona> personas, List<Usuario> usuarios, List<Administrador> administradores) {
        this.personas = personas;
        this.usuarios = usuarios;
        this.administradores = administradores;
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