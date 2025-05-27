package co.edu.uniquindio.poo.billeteravirtual.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PresupuestoController {

    public AnchorPane ap_presupuesto;
    public ImageView img_logo;
    public Text txt_titulo;
    public Text txt_subtitulo;
    public TextField txf_nombre;
    public TextField txf_montoAsignado;
    public TableView tb_listaPresupuestos;
    public TableColumn tbc_nombre;
    public TableColumn tbc_montoAsignado;
    public TableColumn tbc_montoGastado;
    public TableColumn tbc_montoRestante;
    public Button btn_volver;
    public Button btn_eliminarPresupuesto;
    public Button btn_actualizar;
    public Button btn_agregarPresupuesto;

    public void onVolver(ActionEvent actionEvent) {

    }

    public void onEliminarPresupuesto(ActionEvent actionEvent) {

    }

    public void onActualizar(ActionEvent actionEvent) {

    }

    public void onAgregarPresupuesto(ActionEvent actionEvent) {

    }
}