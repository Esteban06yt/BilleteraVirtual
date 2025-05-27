package co.edu.uniquindio.poo.billeteravirtual.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdministradorViewController {


        @FXML
        private TableColumn<?, ?> tbc_telefono;

        @FXML
        private TableView<?> tb_usuario;

        @FXML
        private Button btn_volver;

        @FXML
        private TableColumn<?, ?> tbc_rol;

        @FXML
        private Text txt_titulo;

        @FXML
        private Button btn_crudUsuarios;

        @FXML
        private TableColumn<?, ?> tbc_nombre;

        @FXML
        private ImageView img_logo;

        @FXML
        private Button btn_modificarPerfil;

        @FXML
        private Button btn_actualizar;

        @FXML
        private AnchorPane ap_administrador;

        @FXML
        private TableColumn<?, ?> tbc_cedula;

        @FXML
        private TableColumn<?, ?> tbc_correo;

        @FXML
        private Button btn_verGraficas;

//        @FXML
//        void 231e7f(
//        ActionEvent event) {
//
//        }

        @FXML
        void onVolver(ActionEvent event) {

        }

        @FXML
        void onActualizar(ActionEvent event) {

        }

        @FXML
        void onCrudUsuarios(ActionEvent event) {

        }

        @FXML
        void onVerGraficas(ActionEvent event) {

        }

        @FXML
        void onModificarPerfil(ActionEvent event) {

        }


}