package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.controller.AdministradorController;
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
        private AnchorPane ap_administrador;

        @FXML
        private Button btn_volver, btn_crudUsuarios, btn_crudAdmins, btn_modificarPerfil, btn_verGraficas;

        @FXML
        private TableView<Administrador> tb_administrador;

        @FXML
        private TableColumn<Administrador, String> tbc_nombre, tbc_cedula, tbc_telefono, tbc_correo, tbc_rol;

        @FXML
        private Text txt_titulo;

        @FXML
        private ImageView img_logo;

        private App app;

        private AdministradorController administradorController;

        private final ObservableList<Administrador> listAdministrador = FXCollections.observableArrayList();

        public void setApp(App app) {
                this.app = app;
        }

        @FXML
        void initialize() {
                if (app == null || app.billetera == null) {
                        System.err.println("Error: app o billetera no inicializados.");
                        return;
                }

                administradorController = new AdministradorController(app.billetera);
                initView();
        }

        private void initView() {
                initDataBinding();
                obtenerAdministradorSesion();
                tb_administrador.setItems(listAdministrador);
        }

        private void initDataBinding() {
                tbc_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCompleto()));
                tbc_cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
                tbc_telefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
                tbc_correo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
                tbc_rol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRol().name()));
        }

        private void obtenerAdministradorSesion() {
                Object sesion = app.getSesionActual();

                if (sesion instanceof Administrador administradorSesion) {
                        listAdministrador.clear();
                        listAdministrador.add(administradorSesion);
                } else {
                        System.err.println("No hay administrador en sesión o tipo incorrecto.");
                }
        }

        // Acciones de botones

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
}