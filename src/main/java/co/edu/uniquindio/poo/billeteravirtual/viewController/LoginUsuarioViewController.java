package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.UsuarioSession;
import co.edu.uniquindio.poo.billeteravirtual.observer.UsuarioObservador;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class LoginUsuarioViewController {

        @FXML private Button btn_olvideContrasenia;
        @FXML private Button btn_crearCuenta;
        @FXML private AnchorPane ap_loginUsuario;
        @FXML private TextField txf_correo;
        @FXML private Button btn_volver;
        @FXML private Text txt_iniciarSesion;
        @FXML private Button btn_ingresar;
        @FXML private TextField txf_contrasenia;
        @FXML private Text txt_usuario;

        App app;
        private final SistemaBilleteraFacade facade = new SistemaBilleteraFacade();

        public void setApp(App app) {
                this.app = app;
        }

        @FXML
        void onIngresar(ActionEvent event) {
                String correo = txf_correo.getText();
                String contrasenia = txf_contrasenia.getText();

                Usuario usuario = facade.autenticarUsuario(correo, contrasenia);
                if (usuario != null) {
                        UsuarioSession.getInstancia().setUsuario(usuario);
                        BilleteraVirtual.getInstance().getGestorTransacciones().getNotificador().agregarObservador(new UsuarioObservador(usuario));

                        // ⚠️ Llama a inicializarData aquí, no antes
                        app.inicializarData();
                        app.openUsuario();
                } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error de autenticación");
                        alert.setHeaderText(null);
                        alert.setContentText("Correo o contraseña incorrectos");
                        alert.showAndWait();
                }
        }

        @FXML
        void onCrearCuenta(ActionEvent event) {
                app.openCrearUsuario();
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openLogin();
        }

        @FXML
        void onOlvideContrasenia(ActionEvent event) {
                app.openRecuperacionContrasenia();
        }
}