package co.edu.uniquindio.poo.billeteravirtual.viewController;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class CrudUsuarioViewController {


        @FXML
        private TableColumn<?, ?> tbc_telefono;

        @FXML
        private TextField txf_nombre;

        @FXML
        private TableColumn<?, ?> tbc_saldo;

        @FXML
        private Button btn_agregarUsuario;

        @FXML
        private Button btn_volver;

        @FXML
        private TableView<?> tb_listaUsuarios;

        @FXML
        private Button btn_actualizarUsuario;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private TableColumn<?, ?> tbc_direccion;

        @FXML
        private TextField txf_direccion;

        @FXML
        private Button btn_verCuentas;

        @FXML
        private TableColumn<?, ?> tbc_nombre;

        @FXML
        private TextField txf_telefono;

        @FXML
        private ImageView img_logo;

        @FXML
        private TextField txf_cedula;

        @FXML
        private AnchorPane ap_crudUsuario;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_eliminarUsuario;

        @FXML
        private TextField txf_contrasenia;

        @FXML
        private TableColumn<?, ?> tbc_cedula;

        @FXML
        private TableColumn<?, ?> tbc_correo;

//        @FXML
//        void 231e7f(ActionEvent event) {
//
//        }
//
//        @FXML
//        void 27b0da(ActionEvent event) {
//
//        }

        @FXML
        void onVolver(ActionEvent event) {

        }

        @FXML
        void onEliminarUsuario(ActionEvent event) {

        }

        @FXML
        void onActualizarUsuario(ActionEvent event) {

        }

        @FXML
        void onAgregarUsuario(ActionEvent event) {

        }

        @FXML
        void onVerCuentas(ActionEvent event) {

        }


}