package co.edu.uniquindio.poo.billeteravirtual.model;

public class Session {
    private static Session instancia;
    private Usuario usuario;

    public static Session getINSTANCIA() {
        return instancia;
    }

    public static void setINSTANCIA(Session instancia) {
        Session.instancia = instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private Session() {
    }

    public static Session getInstancia() {
        if (instancia == null) {
            instancia = new Session();
        }
        return instancia;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}