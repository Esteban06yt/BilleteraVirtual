package co.edu.uniquindio.poo.billeteravirtual.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CrudAdministradorController {

    public AnchorPane ap_crudUsuario;
    public Text txt_titulo;
    public Text txt_subtitulo;
    public ImageView img_logo;
    public TableView tb_listaAdministradores;
    public TableColumn tbc_nombre;
    public TableColumn tbc_cedula;
    public TableColumn tbc_telefono;
    public TableColumn tbc_correo;
    public TableColumn tbc_rol;
    public TextField txf_nombre;
    public TextField txf_cedula;
    public TextField txf_telefono;
    public TextField txf_correo;
    public TextField txf_contrasenia;
    public ComboBox cmb_rol;
    public Button btn_volver;
    public Button btn_eliminarAdministrador;
    public Button btn_actualizarAdministrador;
    public Button btn_agregarAdministrador;

    public void onVolver(ActionEvent actionEvent) {

    }

    public void onEliminar(ActionEvent actionEvent) {

    }

    public void onActualizar(ActionEvent actionEvent) {

    }

    public void onAgregar(ActionEvent actionEvent) {

    }
}