package co.edu.uniquindio.poo.billeteravirtual.viewController;

import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CrudAdministradorViewController {

        @FXML
        private TableColumn<?, ?> tbc_telefono;

        @FXML
        private TextField txf_nombre;

        @FXML
        private Button btn_actualizarAdministrador;

        @FXML
        private TableColumn<?, ?> tbc_rol;

        @FXML
        private Button btn_volver;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private TableView<?> tb_listaAdministradores;

        @FXML
        private TableColumn<?, ?> tbc_nombre;

        @FXML
        private TextField txf_telefono;

        @FXML
        private ComboBox<?> cmb_rol;

        @FXML
        private ImageView img_logo;

        @FXML
        private TextField txf_cedula;

        @FXML
        private AnchorPane ap_crudUsuario;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_eliminarAdministrador;

        @FXML
        private TextField txf_contrasenia;

        @FXML
        private Button btn_agregarAdministrador;

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
        void onEliminar(ActionEvent event) {

        }

        @FXML
        void onActualizar(ActionEvent event) {

        }

        @FXML
        void onAgregar(ActionEvent event) {

        }


}