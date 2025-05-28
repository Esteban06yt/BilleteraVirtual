package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.poo.billeteravirtual.model.AdministradorSession;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class LoginAdministradorViewController {

        @FXML
        private Button btn_olvideContrasenia;

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
        private Text txt_administrador;

        @FXML
        private AnchorPane ap_loginAdministrador;

        private App app;
        private final SistemaBilleteraFacade facade = new SistemaBilleteraFacade();

        public void setApp(App app) {
                this.app = app;
        }

        @FXML
        void onIngresar(ActionEvent event) {
                String correo = txf_correo.getText();
                String contrasenia = txf_contrasenia.getText();

                // 1. Autenticar
                Administrador administrador = facade.autenticarAdministrador(correo, contrasenia);
                if (administrador != null) {
                        // 2. Guardar en sesión
                        AdministradorSession.getInstancia().setAdministrador(administrador);

                        // 3. Registrar observador para alertas en UI
                        BilleteraVirtual.getInstance();

                        // 4. Abrir la vista principal de administrador
                        app.openAdministrador();
                } else {
                        // Credenciales inválidas → mostrar error
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error de autenticación");
                        alert.setHeaderText(null);
                        alert.setContentText("Correo o contraseña incorrectos");
                        alert.showAndWait();
                }
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