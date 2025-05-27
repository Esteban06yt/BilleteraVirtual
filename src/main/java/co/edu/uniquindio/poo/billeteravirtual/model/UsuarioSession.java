package co.edu.uniquindio.poo.billeteravirtual.model;

public class UsuarioSession {
    private static UsuarioSession instancia;
    private Usuario usuario;

    public static UsuarioSession getINSTANCIA() {
        return instancia;
    }

    public static void setINSTANCIA(UsuarioSession instancia) {
        UsuarioSession.instancia = instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private UsuarioSession() {
    }

    public static UsuarioSession getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSession();
        }
        return instancia;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}