package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class RecuperacionContraseniaViewController {



        @FXML
        private AnchorPane ap_recuperacionContrasenia;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_enviarCodigo;

        @FXML
        private Button btn_volver;

        @FXML
        private TextField txf_codigo;

        @FXML
        private Text txt_iniciarSesion;

        @FXML
        private Text txt_usuario;

        @FXML
        private TextField txf_contrasenia;

        @FXML
        private Button btn_cambiarContrasenia;

        @FXML
        void onVolver(ActionEvent event) {
                app.openLogin();
        }

        @FXML
        void onCambiarContrasenia(ActionEvent event) {

        }

        @FXML
        void onEnviarCodigo(ActionEvent event) {

        }


        App app;

        public void setApp(App app) {
                this.app = app;
        }
}