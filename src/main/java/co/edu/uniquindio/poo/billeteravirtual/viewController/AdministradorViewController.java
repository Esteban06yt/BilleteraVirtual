package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.controller.AdministradorController;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AdministradorViewController {

        @FXML
        private TableView<Administrador> tb_administrador;

        @FXML
        private Button btn_volver;
        
        @FXML
        private Text txt_titulo;

        @FXML
        private Button btn_crudUsuarios;

        @FXML
        private ImageView img_logo;

        @FXML
        private Button btn_modificarPerfil;
        
        @FXML
        private AnchorPane ap_administrador;
        
        @FXML
        private Button btn_verGraficas;

        @FXML
        private TableView<Administrador> tb_usuario;

        @FXML
        private TableColumn<Administrador, String> tbc_nombre;

        @FXML
        private TableColumn<Administrador, String> tbc_cedula;

        @FXML
        private TableColumn<Administrador, String> tbc_telefono;

        @FXML
        private TableColumn<Administrador, String> tbc_correo;

        @FXML
        private TableColumn<Administrador, RolAdministrador> tbc_rol;

        @FXML
        private Button btn_actualizar;

        private AdministradorController administradorController;

        @FXML
        public void initialize() {
                administradorController = new AdministradorController();

                tbc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
                tbc_cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
                tbc_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                tbc_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
                tbc_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));

                cargarAdministrador();
        }

        private void cargarAdministrador() {
                //ObservableList<Administrador> admin = administradorController.getAdministradorLogueado();
                //tb_usuario.setItems(admin);
        }

        @FXML
        void onVolver(ActionEvent event) {

        }

        @FXML
        void onActualizar(ActionEvent event) {
                //administradorController.actualizarAdministrador();
                cargarAdministrador();
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

        App app;

        public void setApp(App app) {
                this.app = app;
        }

}
