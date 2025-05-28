package co.edu.uniquindio.poo.billeteravirtual.observer;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.ObservadorTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Muestra una alerta en pantalla cuando el usuario recibe una transacción.
 */
public class UsuarioObservador implements ObservadorTransaccion {
    private final Usuario usuario;

    public UsuarioObservador(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void actualizar(Transaccion transaccion) {
        // Solo reacciona si el usuario es emisor o destinatario
        if (transaccion.getEmisor().equals(usuario) || transaccion.getDestinatario().equals(usuario)) {
            Platform.runLater(() -> {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Nueva Transacción");
                alerta.setHeaderText("Tienes una nueva transacción");
                alerta.setContentText(
                        String.format("ID: %s\nTipo: %s\nMonto: %.2f",
                                transaccion.getIdTransaccion(),
                                transaccion.getTipo().name(),
                                transaccion.getMonto()
                        )
                );
                alerta.showAndWait();
            });
        }
    }
}