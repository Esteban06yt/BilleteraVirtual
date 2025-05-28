package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.facade.SistemaBilleteraFacade;
import co.edu.uniquindio.poo.billeteravirtual.enums.RolAdministrador;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CrudAdministradorViewController {

        @FXML private AnchorPane ap_crudUsuario;
        @FXML private Text txt_titulo, txt_subtitulo;
        @FXML private ImageView img_logo;

        @FXML private TableView<Administrador> tb_listaAdministradores;
        @FXML private TableColumn<Administrador,String> tbc_nombre, tbc_cedula, tbc_telefono, tbc_correo, tbc_rol;

        @FXML private TextField txf_nombre, txf_cedula, txf_telefono, txf_correo, txf_contrasenia;
        @FXML private ComboBox<RolAdministrador> cmb_rol;

        @FXML private Button btn_volver, btn_agregarAdministrador, btn_actualizarAdministrador, btn_eliminarAdministrador;

        private App app;
        private SistemaBilleteraFacade fachada;
        private ObservableList<Administrador> administradores;

        public void setApp(App app) {
                this.app = app;
                this.fachada = new SistemaBilleteraFacade();
                inicializarVista();
        }

        @FXML
        void initialize() {
                // nothing: se inicializa en setApp()
        }

        private void inicializarVista() {
                // 1) Combo de roles
                cmb_rol.setItems(FXCollections.observableArrayList(RolAdministrador.values()));

                // 2) Configurar columnas de la tabla
                tbc_nombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCompleto()));
                tbc_cedula.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCedula()));
                tbc_telefono.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
                tbc_correo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
                tbc_rol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRol().name()));

                // 3) Cargar datos
                administradores = FXCollections.observableArrayList(fachada.listarAdministradores());
                tb_listaAdministradores.setItems(administradores);

                // 4) Cuando selecciono un admin en la tabla, cargo sus datos en el formulario
                tb_listaAdministradores.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
                        if (sel != null) {
                                txf_nombre.setText(sel.getNombreCompleto());
                                txf_cedula.setText(sel.getCedula());
                                txf_telefono.setText(sel.getTelefono());
                                txf_correo.setText(sel.getCorreo());
                                txf_contrasenia.setText(sel.getContrasenia());
                                cmb_rol.setValue(sel.getRol());
                                // inhabilitar edición de cédula, pues es clave primaria
                                txf_cedula.setDisable(true);
                        }
                });
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openAdministrador();
        }

        @FXML
        void onAgregar(ActionEvent event) {
                try {
                        String ced = txf_cedula.getText().trim();
                        String nom = txf_nombre.getText().trim();
                        String tel = txf_telefono.getText().trim();
                        String mail = txf_correo.getText().trim();
                        String pwd = txf_contrasenia.getText();
                        RolAdministrador rol = cmb_rol.getValue();

                        Administrador nuevo = new Administrador(
                                ced, nom, mail, tel, pwd, rol
                        );
                        fachada.registrarAdministrador(nuevo);
                        administradores.add(nuevo);
                        limpiarFormulario();
                        showAlert("Éxito", "Administrador agregado.");
                } catch (Exception ex) {
                        showAlert("Error", ex.getMessage());
                }
        }

        @FXML
        void onActualizar(ActionEvent event) {
                Administrador sel = tb_listaAdministradores.getSelectionModel().getSelectedItem();
                if (sel == null) {
                        showAlert("Error", "Selecciona un administrador.");
                        return;
                }
                try {
                        String nom = txf_nombre.getText().trim();
                        String tel = txf_telefono.getText().trim();
                        String mail = txf_correo.getText().trim();
                        String pwd = txf_contrasenia.getText();
                        RolAdministrador rol = cmb_rol.getValue();

                        boolean ok = fachada.actualizarAdministrador(
                                sel.getCedula(), nom, mail, tel, pwd, rol
                        );
                        if (ok) {
                                // refrescar vista
                                sel.setNombreCompleto(nom);
                                sel.setTelefono(tel);
                                sel.setCorreo(mail);
                                sel.setContrasenia(pwd);
                                sel.setRol(rol);
                                tb_listaAdministradores.refresh();
                                showAlert("Éxito", "Administrador actualizado.");
                        } else {
                                showAlert("Error", "No se pudo actualizar.");
                        }
                } catch (Exception ex) {
                        showAlert("Error", ex.getMessage());
                }
        }

        @FXML
        void onEliminar(ActionEvent event) {
                Administrador sel = tb_listaAdministradores.getSelectionModel().getSelectedItem();
                if (sel == null) {
                        showAlert("Error", "Selecciona un administrador.");
                        return;
                }
                boolean ok = fachada.eliminarAdministrador(sel.getCedula());
                if (ok) {
                        administradores.remove(sel);
                        limpiarFormulario();
                        showAlert("Éxito", "Administrador eliminado.");
                } else {
                        showAlert("Error", "No se pudo eliminar.");
                }
        }

        private void limpiarFormulario() {
                txf_nombre.clear();
                txf_cedula.clear();
                txf_telefono.clear();
                txf_correo.clear();
                txf_contrasenia.clear();
                cmb_rol.getSelectionModel().clearSelection();
                txf_cedula.setDisable(false);
                tb_listaAdministradores.getSelectionModel().clearSelection();
        }

        private void showAlert(String title, String msg) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle(title);
                a.setHeaderText(null);
                a.setContentText(msg);
                a.showAndWait();
        }
}