package co.edu.uniquindio.poo.billeteravirtual.model;

public class AdministradorSession {
    private static AdministradorSession instancia;
    private Administrador administrador;

    private AdministradorSession() {
    }

    public static AdministradorSession getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorSession();
        }
        return instancia;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void cerrarSesion() {
        administrador = null;
    }
}