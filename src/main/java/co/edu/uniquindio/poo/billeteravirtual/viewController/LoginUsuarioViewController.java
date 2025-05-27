package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class LoginUsuarioViewController {

        @FXML
        private Button btn_olvideContrasenia;

        @FXML
        private Button btn_crearCuenta;

        @FXML
        private AnchorPane ap_loginUsuario;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_volver;

        @FXML
        private Text txt_iniciarSesion;

        @FXML
        private Button btn_ingresar;

        @FXML
        private TextField txf_contrasenia;

        @FXML
        private Text txt_usuario;

        @FXML
        void onIngresar(ActionEvent event) {
                app.openUsuario();
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

        App app;

        public void setApp(App app) {
                this.app = app;
        }

}