package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.controller.AdministradorController;
import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        private TableColumn<Administrador, String> tbc_telefono;

        @FXML
        private Button btn_volver;

        @FXML
        private TableView<Administrador> tb_administrador;

        @FXML
        private TableColumn<Administrador, String> tbc_rol;

        @FXML
        private Text txt_titulo;

        @FXML
        private Button btn_crudUsuarios;

        @FXML
        private TableColumn<Administrador, String> tbc_nombre;

        @FXML
        private ImageView img_logo;

        @FXML
        private Button btn_modificarPerfil;

        @FXML
        private Button btn_actualizar;

        @FXML
        private AnchorPane ap_administrador;

        @FXML
        private TableColumn<Administrador, String> tbc_cedula;

        @FXML
        private TableColumn<Administrador, String> tbc_correo;

        @FXML
        private Button btn_verGraficas;

        @FXML
        void onVolver(ActionEvent event) {
                app.openLoginAdministrador();
        }

        @FXML
        void onCrudAdmins(ActionEvent event) {
                app.openCrudAdministrador();
        }

        @FXML
        void onCrudUsuarios(ActionEvent event) {
                app.openCrearUsuario();
        }

        @FXML
        void onVerGraficas(ActionEvent event) {
                app.openGraficas();
        }

        @FXML
        void onModificarPerfil(ActionEvent event) {
                app.openModificarPerfilAdministrador();
        }

        App app;

        public void setApp(App app) {
                this.app = app;
        }

        AdministradorController administradorController;
        ObservableList<Administrador> listAdministrador = FXCollections.observableArrayList();

        @SuppressWarnings("static-access")
        @FXML
        void initialize() {
                administradorController = new AdministradorController(app.billetera);
                initView();
        }


        private void initView() {
                // Traer los datos del Administrador a la tabla
                initDataBinding();

                // Obtiene la lista
                obtenerAdministrador();

                // Agregar los elementos a la tabla
                tb_administrador.setItems(listAdministrador);
        }

        private void initDataBinding() {
                tbc_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCompleto()));
                tbc_cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
                tbc_telefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
                tbc_correo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
                tbc_rol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRol().name()));
                // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
        }

        private void obtenerAdministrador() {
                Administrador administrador = new Administrador("123456789", "Andres Arboleda", "Andres@gmail.com", "+57 3207249141","Andres123", RolAdministrador.SUPERADMIN);
                listAdministrador.add(administrador);
        }

}
