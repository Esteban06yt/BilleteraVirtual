package co.edu.uniquindio.poo.billeteravirtual.model;

public class UsuarioSession {
    private static UsuarioSession instancia;
    private Usuario usuario;

    private UsuarioSession() {
    }

    public static UsuarioSession getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSession();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}