package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ModificarPerfilAdministradorViewController {

        @FXML
        private TextField txf_nombre;

        @FXML
        private TextField txf_telefono;

        @FXML
        private ImageView img_logo;

        @FXML
        private TextField txf_cedula;

        @FXML
        private Button btn_actualizarAdministrador;

        @FXML
        private AnchorPane ap_modificarPerfil;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_volver;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private TextField txf_contrasenia;

        @FXML
        void onVolver(ActionEvent event) {
                app.openAdministrador();
        }

        @FXML
        void onActualizarAdministrador(ActionEvent event) {

        }


        App app;

        public void setApp(App app) {
                this.app = app;
        }

}
