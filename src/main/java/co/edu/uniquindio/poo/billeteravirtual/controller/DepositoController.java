package co.edu.uniquindio.poo.billeteravirtual.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class DepositoController {

    public AnchorPane ap_deposito;
    public Text txt_titulo;
    public Text txt_subtitulo;
    public Text txt_subtitulo1;
    public ImageView img_logo;
    public TableView tb_listaCuentas;
    public TableColumn tbc_nombreBanco;
    public TableColumn tbc_numeroCuenta;
    public TableColumn tbc_monto;
    public TableColumn tbc_tipo;
    public TextField txf_monto;
    public TextField txf_descripcion;
    public ComboBox cmb_categoria;
    public Button btn_volver;
    public Button btn_realizarDeposito;
    public Button btn_actualizar;

    public void onVolver(ActionEvent actionEvent) {

    }

    public void onRealizarDeposito(ActionEvent actionEvent) {

    }

    public void onActualizar(ActionEvent actionEvent) {

    }
}