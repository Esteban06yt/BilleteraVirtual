package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ModificarPerfilAdministradorViewController {

        @FXML
        private TextField txf_nombre;

        @FXML
        private TextField txf_telefono;

        @FXML
        private ImageView img_logo;

        @FXML
        private TextField txf_cedula;

        @FXML
        private Button btn_actualizarAdministrador;

        @FXML
        private AnchorPane ap_modificarPerfil;

        @FXML
        private TextField txf_correo;

        @FXML
        private Button btn_volver;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private TextField txf_contrasenia;

        private Administrador administrador;

        public void setAdministrador(Administrador administrador) {
                this.administrador = administrador;
                cargarDatos();
        }
        private void cargarDatos() {
                if (administrador != null) {
                        txf_nombre.setText(administrador.getNombreCompleto());
                        txf_telefono.setText(administrador.getTelefono());
                        txf_cedula.setText(administrador.getCedula());
                        txf_correo.setText(administrador.getCorreo());
                        txf_contrasenia.setText(administrador.getContrasenia());
                }
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openAdministrador();
        }

        @FXML
        void onActualizarAdministrador(ActionEvent event){
                administrador.setNombreCompleto(txf_nombre.getText());
                administrador.setTelefono(txf_telefono.getText());
                administrador.setCedula(txf_cedula.getText());
                administrador.setCorreo(txf_correo.getText());
                administrador.setContrasenia(txf_contrasenia.getText());

                // Puedes mostrar un mensaje de éxito o usar un Alert
                System.out.println("Perfil actualizado correctamente");

                // Volver a la vista principal
                app.openAdministrador();


        }


        App app;

        public void setApp(App app) {
                this.app = app;
        }

}
