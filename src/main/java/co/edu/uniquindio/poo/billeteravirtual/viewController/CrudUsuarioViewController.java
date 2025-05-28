package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.BilleteraVirtual;
import co.edu.uniquindio.poo.billeteravirtual.model.Cuenta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import co.edu.uniquindio.poo.billeteravirtual.model.UsuarioSession;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CrudUsuarioViewController {

        @FXML private AnchorPane ap_crudUsuario;
        @FXML private Text txt_titulo, txt_subtitulo;
        @FXML private ImageView img_logo;

        // Tabla de usuarios
        @FXML private TableView<Usuario> tb_listaUsuarios;
        @FXML private TableColumn<Usuario, String> tbc_nombre, tbc_cedula, tbc_telefono, tbc_correo, tbc_direccion;
        @FXML private TableColumn<Usuario, Number> tbc_saldo;

        // Formulario
        @FXML private TextField txf_nombre, txf_cedula, txf_telefono, txf_correo, txf_contrasenia, txf_direccion;

        // Botones
        @FXML private Button btn_volver, btn_agregarUsuario, btn_actualizarUsuario, btn_eliminarUsuario, btn_verCuentas;

        private App app;
        private final BilleteraVirtual sistema = App.billetera;
        private final ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

        public void setApp(App app) {
                this.app = app;
                initTable();
                loadUsuarios();
        }

        @FXML
        void initialize() {
                // nothing: se inicializa en setApp()
        }

        private void initTable() {
                tbc_nombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCompleto()));
                tbc_cedula.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCedula()));
                tbc_telefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
                tbc_correo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
                tbc_direccion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDireccion()));
                tbc_saldo.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSaldo()));
                tb_listaUsuarios.setItems(usuarios);

                // Al seleccionar un usuario en la tabla, llenar el formulario
                tb_listaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldU, newU) -> {
                        if (newU != null) {
                                txf_nombre.setText(newU.getNombreCompleto());
                                txf_cedula.setText(newU.getCedula());
                                txf_telefono.setText(newU.getTelefono());
                                txf_correo.setText(newU.getCorreo());
                                txf_contrasenia.setText(newU.getContrasenia());
                                txf_direccion.setText(newU.getDireccion());
                        }
                });
        }

        private void loadUsuarios() {
                usuarios.setAll(sistema.getGestorUsuarios().getUsuarios());
        }

        @FXML
        void onAgregarUsuario(ActionEvent event) {
                try {
                        Usuario u = new Usuario(
                                txf_cedula.getText(),
                                txf_nombre.getText(),
                                txf_correo.getText(),
                                txf_telefono.getText(),
                                txf_contrasenia.getText(),
                                txf_direccion.getText(),
                                null, null, null, null
                        );
                        sistema.getGestorUsuarios().agregarUsuario(u);
                        loadUsuarios();
                        clearForm();
                        showAlert("Éxito", "Usuario agregado correctamente.");
                } catch (IllegalArgumentException ex) {
                        showAlert("Error al agregar", ex.getMessage());
                }
        }

        @FXML
        void onActualizarUsuario(ActionEvent event) {
                Usuario sel = tb_listaUsuarios.getSelectionModel().getSelectedItem();
                if (sel == null) {
                        showAlert("Atención", "Selecciona un usuario para actualizar.");
                        return;
                }
                boolean ok = sistema.getGestorUsuarios().actualizarUsuario(
                        sel.getCedula(),
                        txf_nombre.getText(),
                        txf_correo.getText(),
                        txf_telefono.getText(),
                        txf_contrasenia.getText(),
                        txf_direccion.getText()
                );
                if (ok) {
                        loadUsuarios();
                        clearForm();
                        showAlert("Éxito", "Usuario actualizado.");
                } else {
                        showAlert("Error", "No se pudo actualizar.");
                }
        }

        @FXML
        void onEliminarUsuario(ActionEvent event) {
                Usuario sel = tb_listaUsuarios.getSelectionModel().getSelectedItem();
                if (sel == null) {
                        showAlert("Atención", "Selecciona un usuario para eliminar.");
                        return;
                }
                boolean ok = sistema.getGestorUsuarios().eliminarUsuario(sel.getCedula());
                if (ok) {
                        loadUsuarios();
                        clearForm();
                        showAlert("Éxito", "Usuario eliminado.");
                } else {
                        showAlert("Error", "No se pudo eliminar.");
                }
        }

        @FXML
        void onVerCuentas(ActionEvent event) {
                Usuario sel = tb_listaUsuarios.getSelectionModel().getSelectedItem();
                if (sel == null) {
                        showAlert("Atención", "Selecciona un usuario para ver sus cuentas.");
                        return;
                }
                // Guardamos en la sesión el usuario seleccionado
                UsuarioSession.getInstancia().setUsuario(sel);
                app.openCrudCuenta();
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openAdministrador();
        }

        private void clearForm() {
                txf_nombre.clear();
                txf_cedula.clear();
                txf_telefono.clear();
                txf_correo.clear();
                txf_contrasenia.clear();
                txf_direccion.clear();
                tb_listaUsuarios.getSelectionModel().clearSelection();
        }

        private void showAlert(String title, String msg) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle(title);
                a.setHeaderText(null);
                a.setContentText(msg);
                a.showAndWait();
        }

        public void onAgregarCuenta(ActionEvent actionEvent) {

        }
}