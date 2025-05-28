package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.UUID;

public class TransferenciaViewController {

    @FXML private AnchorPane ap_transferencia;
    @FXML private Text txt_titulo, txt_subtitulo1;
    @FXML private ImageView img_logo;

    @FXML private TableView<Cuenta> tb_listaCuentas;
    @FXML private TableColumn<Cuenta, String> tbc_nombreBanco, tbc_numeroCuenta;
    @FXML private TableColumn<Cuenta, Number> tbc_montoCuenta;
    @FXML private TableColumn<Cuenta, String> tbc_tipoCuenta;

    @FXML private TextField txf_numeroCuentaDestino, txf_monto, txf_descripcion;
    @FXML private ComboBox<Categoria> cmb_categoria;

    @FXML private Button btn_volver, btn_realizarTransferencia, btn_actualizar;

    private App app;
    private Usuario usuarioActual;
    private ObservableList<Cuenta> cuentas = FXCollections.observableArrayList();

    public void setApp(App app) {
        this.app = app;
        // recuperar usuario de la sesión
        usuarioActual = (Usuario) app.getSesionActual();
        if (usuarioActual == null) {
            app.openLoginUsuario();
            return;
        }
        initTable();
        loadCategorias();
        refreshTable();
    }

    @FXML
    void initialize() {
        // nothing: se inicializa en setApp()
    }

    private void initTable() {
        tbc_nombreBanco.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreBanco()));
        tbc_numeroCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIdCuenta()));
        tbc_montoCuenta.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMonto()));
        tbc_tipoCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().name()));
        tb_listaCuentas.setItems(cuentas);
    }

    private void loadCategorias() {
        cmb_categoria.setItems(FXCollections.observableArrayList(app.billetera.getGestorCategorias().listarCategorias()));
    }

    private void refreshTable() {
        cuentas.setAll(usuarioActual.getCuentas());
    }

    @FXML
    void onVolver(ActionEvent event) {
        app.openUsuario();
    }

    @FXML
    void onActualizar(ActionEvent event) {
        refreshTable();
    }

    @FXML
    void onRealizarTransferencia(ActionEvent event) {
        Cuenta cuentaOrigen = tb_listaCuentas.getSelectionModel().getSelectedItem();
        if (cuentaOrigen == null) {
            showAlert("Error", "Selecciona la cuenta de origen.");
            return;
        }

        String destNum = txf_numeroCuentaDestino.getText();
        String desc    = txf_descripcion.getText();
        Categoria cat  = cmb_categoria.getValue();
        double   monto;
        try {
            monto = Double.parseDouble(txf_monto.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Monto inválido.");
            return;
        }

        if (destNum.isBlank()) {
            showAlert("Error", "Ingresa número de cuenta destino.");
            return;
        }
        if (cat == null) {
            showAlert("Error", "Selecciona una categoría.");
            return;
        }

        // buscar destinatario por número de cuenta
        Cuenta destinatario = app.billetera.getGestorCuentas().buscarCuentaPorId(destNum);
        if (destinatario == null) {
            showAlert("Error", "Cuenta destino no encontrada.");
            return;
        }

        try {
            app.billetera.getGestorTransacciones().agregarTransaccion(
                    UUID.randomUUID().toString(),
                    monto,
                    desc,
                    TipoTransaccion.TRANSFERENCIA,
                    cat,
                    usuarioActual,
                    destinatario,
                    cuentaOrigen.getIdCuenta()
            );
            showAlert("Éxito", "Transferencia realizada.");
            clearFields();
            refreshTable();
        } catch (IllegalArgumentException ex) {
            showAlert("Error", ex.getMessage());
        }
    }

    private void clearFields() {
        txf_numeroCuentaDestino.clear();
        txf_monto.clear();
        txf_descripcion.clear();
        cmb_categoria.getSelectionModel().clearSelection();
        tb_listaCuentas.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }
}