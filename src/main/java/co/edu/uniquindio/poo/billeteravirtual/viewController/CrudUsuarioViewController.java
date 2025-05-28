package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.controller.CrudUsuarioController;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        private TableColumn<Usuario, String> tbc_telefono;

        @FXML
        private TextField txf_nombre;

        @FXML
        private TableColumn<Usuario, String> tbc_saldo;

        @FXML
        private Button btn_agregarUsuario;

        @FXML
        private Button btn_volver;

        @FXML
        private TableView<Usuario> tb_listaUsuarios;

        @FXML
        private Button btn_actualizarUsuario;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private TableColumn<Usuario, String> tbc_direccion;

        @FXML
        private TextField txf_direccion;

        @FXML
        private Button btn_verCuentas;

        @FXML
        private TableColumn<Usuario, String> tbc_nombre;

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
        private TableColumn<Usuario, String> tbc_cedula;

        @FXML
        private TableColumn<Usuario, String> tbc_correo;

        @FXML
        void onVolver(ActionEvent event) {
                app.openAdministrador();
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
                app.openCrudCuenta();
        }


        App app;

        public void setApp(App app) {
                this.app = app;
        }

        CrudUsuarioController crudUsuarioController;
        ObservableList<Usuario> listUsuarios = FXCollections.observableArrayList();
        Usuario selectedUsuario;

        @SuppressWarnings("static-access")
        @FXML
        void initialize() {
                crudUsuarioController = new CrudUsuarioController(app.billetera);
                initView();
        }

        private void initView() {
                // Traer los datos del usuario a la tabla
                initDataBinding();

                // Obtiene la lista
                obtenerUsuarios();

                // Agregar los elementos a la tabla
                tb_listaUsuarios.setItems(listUsuarios);

                // Seleccionar elemento de la tabla
                listenerSelection();
        }

        private void initDataBinding() {
                tbc_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCompleto()));
                tbc_cedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
                tbc_telefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
                tbc_correo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
                tbc_direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
                tbc_saldo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSaldo().toString()));
                // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
        }

        private void obtenerUsuarios() {

        }

        private void listenerSelection() {
                tb_listaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                        selectedUsuario = newSelection;
                        mostrarInformacionUsuario(selectedUsuario);
                });
        }

        private void mostrarInformacionUsuario(Usuario usuario) {
                if (usuario != null) {
                        txf_nombre.setText(usuario.getNombreCompleto());
                        txf_cedula.setText(usuario.getCedula());
                        txf_direccion.setText(usuario.getDireccion());
                        txf_telefono.setText(usuario.getTelefono());
                        txf_correo.setText(usuario.getCorreo());
                        txf_contrasenia.setText(usuario.getContrasenia());
                }
        }

        private void agregarUsuario() {
                Usuario usuario = buildUsuario();
                if (crudUsuarioController.crearUsuario(usuario)) {
                        listUsuarios.add(usuario);
                        limpiarCampos();
                }
        }

        private Usuario buildUsuario() {
                Usuario usuario = new Usuario(txf_nombre.getText(), txf_cedula.getText(), txf_direccion.getText(),
                        txf_telefono.getText(), txf_correo.getText(), txf_contrasenia.getText(),null,null,null);
                return usuario;
        }

        private void eliminarUsuario() {
                if (crudUsuarioController.eliminarUsuario(txf_cedula.getText())) {
                        listUsuarios.remove(selectedUsuario);
                        limpiarCampos();
                        limpiarSeleccion();
                }
        }

        private void actualizarUsuario() {

                if (selectedUsuario != null && crudUsuarioController.actualizarUsuario(selectedUsuario.getCedula(), buildUsuario())) {
                        int index = listUsuarios.indexOf(selectedUsuario);
                        if (index >= 0) {
                                listUsuarios.set(index, buildUsuario());
                        }
                        tb_listaUsuarios.refresh();
                        limpiarSeleccion();
                        limpiarCampos();
                }
        }

        private void limpiarSeleccion() {
                tb_listaUsuarios.getSelectionModel().clearSelection();
                limpiarCampos();
        }

        private void limpiarCampos() {
                txf_nombre.clear();
                txf_cedula.clear();
                txf_telefono.clear();
                txf_correo.clear();
                txf_direccion.clear();
                txf_contrasenia.clear();
        }
}