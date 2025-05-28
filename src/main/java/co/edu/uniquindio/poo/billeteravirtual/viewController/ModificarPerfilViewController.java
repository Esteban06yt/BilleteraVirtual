package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class ModificarPerfilViewController {

        @FXML
        private TextField txf_nombre;

        @FXML
        private TextField txf_telefono;

        @FXML
        private ImageView img_logo;

        @FXML
        private TextField txf_cedula;

        @FXML
        private AnchorPane ap_modificarPerfil;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_volver;

        @FXML
        private Button btn_actualizarUsuario;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private TextField txf_contrasenia;

        @FXML
        private TextField txf_direccion;

        @FXML
        void onVolver(ActionEvent event) {
                app.openUsuario();
        }

        @FXML
        void onActualizarUsuario(ActionEvent event) {
                if (App.usuarioActual != null) {
                        // Obtener los datos desde los TextFields
                        String nombre = txf_nombre.getText();
                        String cedula = txf_cedula.getText();
                        String correo = txf_correo.getText();
                        String contrasenia = txf_contrasenia.getText();
                        String direccion = txf_direccion.getText();
                        String telefono = txf_telefono.getText();

                        // Actualizar el usuario actual
                        App.usuarioActual.setNombreCompleto(nombre);
                        App.usuarioActual.setCedula(cedula);
                        App.usuarioActual.setCorreo(correo);
                        App.usuarioActual.setContrasenia(contrasenia);
                        App.usuarioActual.setDireccion(direccion);
                        App.usuarioActual.setTelefono(telefono);

                        // (Opcional) Guardar en almacenamiento o mostrar mensaje
                        mostrarAlerta("Modificar Usuario","Usuario actualizado correctamente: " + App.usuarioActual);

                } else {
                        mostrarAlerta("Modificar Usuario","No hay un usuario actual para actualizar.");
                }

        }


        App app;

        public void setApp(App app) {
                this.app = app;
        }

        private void mostrarAlerta(String titulo, String contenido) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(titulo);
                alert.setContentText(contenido);
                alert.showAndWait();
        }
}