package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.Cuenta;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoCuenta;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
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

public class CrudCuentaViewController {

        @FXML
        private TableColumn<Cuenta, String> tbc_nombreBanco;

        @FXML
        private TableColumn<Cuenta, Integer> tbc_numeroCuenta;

        @FXML
        private TableColumn<Cuenta, Double> tbc_monto;

        @FXML
        private TableColumn<Cuenta, TipoCuenta> tbc_tipo;

        @FXML
        private TableView<Cuenta> tb_listaCuentas;

        @FXML
        private TextField txf_nombreBanco;

        @FXML
        private ComboBox<TipoCuenta> cmb_tipo;

        @FXML
        private Button btn_volver, btn_eliminarCuenta, btn_agregarCuenta, btn_actualizar;

        @FXML
        private Text txt_subtitulo, txt_titulo;

        @FXML
        private ImageView img_logo;

        @FXML
        private AnchorPane ap_crudCuenta;

        private App app;

        private ObservableList<Cuenta> cuentas = FXCollections.observableArrayList();

        private Usuario usuarioActual;

        public void setApp(App app) {
                this.app = app;
                this.usuarioActual = App.usuarioActual; // ← Obtener usuario de la sesión
                inicializarDatos();
        }

        private void inicializarDatos() {
                if (usuarioActual != null) {
                        cuentas.setAll(usuarioActual.getCuentas());
                        tb_listaCuentas.setItems(cuentas);

                        tbc_nombreBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreBanco()));
                        tbc_numeroCuenta.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumeroCuenta()).asObject());
                        tbc_monto.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMonto()).asObject());
                        tbc_tipo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipo()));

                        cmb_tipo.setItems(FXCollections.observableArrayList(TipoCuenta.values()));
                }
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openAdministrador();
        }

        @FXML
        void onEliminarCuenta(ActionEvent event) {
                Cuenta seleccionada = tb_listaCuentas.getSelectionModel().getSelectedItem();
                if (seleccionada != null) {
                        usuarioActual.getCuentas().remove(seleccionada);
                        cuentas.remove(seleccionada);
                }
        }

        @FXML
        void onAgregarCuenta(ActionEvent event) {
                String banco = txf_nombreBanco.getText();
                TipoCuenta tipo = cmb_tipo.getValue();

                if (banco != null && !banco.isEmpty() && tipo != null) {
                        Cuenta nueva = new Cuenta(banco, tipo);
                        usuarioActual.getCuentas().add(nueva);
                        cuentas.add(nueva);
                        limpiarFormulario();
                }
        }

        @FXML
        void onActualizar(ActionEvent event) {
                Cuenta seleccionada = tb_listaCuentas.getSelectionModel().getSelectedItem();
                if (seleccionada != null) {
                        String nuevoBanco = txf_nombreBanco.getText();
                        TipoCuenta nuevoTipo = cmb_tipo.getValue();
                        if (nuevoBanco != null && !nuevoBanco.isEmpty() && nuevoTipo != null) {
                                seleccionada.setTipo(nuevoTipo);
                                tb_listaCuentas.refresh();
                                limpiarFormulario();
                        }
                }
        }

        private String generarNumeroCuentaUnico() {
                return "CUENTA-" + System.currentTimeMillis();
        }

        private void limpiarFormulario() {
                txf_nombreBanco.clear();
                cmb_tipo.getSelectionModel().clearSelection();
        }
}