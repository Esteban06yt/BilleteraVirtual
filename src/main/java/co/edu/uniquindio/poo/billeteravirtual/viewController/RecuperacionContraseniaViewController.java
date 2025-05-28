package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.Persona;
import co.edu.uniquindio.poo.billeteravirtual.service.EmailService;
import co.edu.uniquindio.poo.billeteravirtual.service.RecuperacionContraseniaService;
import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RecuperacionContraseniaViewController {

        @FXML private AnchorPane ap_recuperacionContrasenia;
        @FXML private TextField txf_correo, txf_codigo, txf_contrasenia;
        @FXML private Button btn_enviarCodigo, btn_volver, btn_cambiarContrasenia;
        @FXML private Text txt_iniciarSesion, txt_usuario;

        private App app;
        private final SistemaBilleteraFacade fachada = new SistemaBilleteraFacade();
        private RecuperacionContraseniaService servicio;
        private Persona persona; // Guarda la persona que va a recuperar contraseña

        public void setApp(App app) {
                this.app = app;
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openLogin();
        }

        @FXML
        void onEnviarCodigo(ActionEvent event) {
                String correo = txf_correo.getText().trim();
                persona = fachada.buscarUsuarioPorCorreo(correo);

                if (persona == null) {
                        mostrarAlerta("Error", "No se encontró ninguna cuenta con ese correo.");
                        return;
                }

                // Generar y guardar código de recuperación
                String codigo = generarCodigo();
                persona.setCodigoRecuperacion(codigo);

                servicio = new RecuperacionContraseniaService(persona, new EmailService("smtp.gmail.com", 587, "estebanpolanco06@gmail.com", "blma ttmu gfjj alfd", true));

                boolean enviado = servicio.enviarCodigoPorEmail();

                if (enviado) {
                        mostrarAlerta("Éxito", "Código de recuperación enviado al correo.");
                } else {
                        mostrarAlerta("Error", "No se pudo enviar el correo. Intenta nuevamente.");
                }
        }

        @FXML
        void onCambiarContrasenia(ActionEvent event) {
                if (persona == null) {
                        mostrarAlerta("Error", "Primero debes enviar el código al correo.");
                        return;
                }

                String codigoIngresado = txf_codigo.getText().trim();
                String nuevaContrasenia = txf_contrasenia.getText();

                boolean exito = servicio.cambiarContrasenia(nuevaContrasenia, codigoIngresado);

                if (exito) {
                        mostrarAlerta("Éxito", "Contraseña actualizada correctamente.");
                        app.openLogin();
                } else {
                        mostrarAlerta("Error", "Código incorrecto. Intenta de nuevo.");
                }
        }

        private String generarCodigo() {
                return String.valueOf((int)(Math.random() * 900000 + 100000)); // Código de 6 dígitos
        }

        private void mostrarAlerta(String titulo, String mensaje) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titulo);
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.showAndWait();
        }
}