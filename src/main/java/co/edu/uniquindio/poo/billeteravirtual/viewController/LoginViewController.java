package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;


public class LoginViewController {

        @FXML
        private Button btn_usuario;

        @FXML
        private Text lbl_bienvenido;

        @FXML
        private AnchorPane ap_login;

        @FXML
        private Text lbl_iniciarComo;

        @FXML
        private Button btn_administrador;

        @FXML
        void onUsuario(ActionEvent event) {
                app.openLoginUsuario();
        }

        @FXML
        void onAdministrador(ActionEvent event) {
                app.openLoginAdministrador();
        }

        App app;

        public void setApp(App app) {
                this.app = app;
        }

}