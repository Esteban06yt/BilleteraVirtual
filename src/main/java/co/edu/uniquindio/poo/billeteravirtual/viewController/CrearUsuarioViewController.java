package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class CrearUsuarioViewController {

        @FXML private TextField txf_nombre;
        @FXML private TextField txf_telefono;
        @FXML private TextField txf_cedula;
        @FXML private TextField txf_correo;
        @FXML private TextField txf_contrasenia;
        @FXML private TextField txf_direccion;

        @FXML private Button btn_agregarUsuario;
        @FXML private Button btn_volver;

        @FXML private ImageView img_logo;
        @FXML private AnchorPane ap_crearUsuario;
        @FXML private Text txt_subtitulo;
        @FXML private Text txt_titulo;

        private App app;

        public void setApp(App app) {
                this.app = app;
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openLoginUsuario();
        }

        @FXML
        void onAgregarUsuario(ActionEvent event) {
                String nombre = txf_nombre.getText().trim();
                String telefono = txf_telefono.getText().trim();
                String cedula = txf_cedula.getText().trim();
                String correo = txf_correo.getText().trim();
                String contrasenia = txf_contrasenia.getText().trim();
                String direccion = txf_direccion.getText().trim();

                // Validaciones básicas
                if(nombre.isEmpty() || telefono.isEmpty() || cedula.isEmpty() || correo.isEmpty() || contrasenia.isEmpty() || direccion.isEmpty()) {
                        mostrarAlerta("Error", "Todos los campos son obligatorios.");
                        return;
                }

                try {
                        Usuario nuevoUsuario = new Usuario(cedula, nombre, correo, telefono, contrasenia, direccion, new ArrayList<>(),  new ArrayList<>(),  new ArrayList<>(),  new ArrayList<>());

                        boolean creado = app.billetera.getGestorUsuarios().agregarUsuario(nuevoUsuario);

                        if(creado) {
                                mostrarAlerta("Éxito", "Usuario creado correctamente.");
                                limpiarCampos();
                                app.openLoginUsuario();
                        } else {
                                mostrarAlerta("Error", "No se pudo crear el usuario (quizá ya existe).");
                        }
                } catch (IllegalArgumentException ex) {
                        mostrarAlerta("Error", ex.getMessage());
                } catch (Exception ex) {
                        mostrarAlerta("Error", "Error inesperado al crear el usuario.");
                        ex.printStackTrace();
                }
        }

        private void limpiarCampos() {
                txf_nombre.clear();
                txf_telefono.clear();
                txf_cedula.clear();
                txf_correo.clear();
                txf_contrasenia.clear();
                txf_direccion.clear();
        }

        private void mostrarAlerta(String titulo, String mensaje) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titulo);
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.showAndWait();
        }
}