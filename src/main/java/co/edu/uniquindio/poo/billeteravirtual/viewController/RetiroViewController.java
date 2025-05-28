package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
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

import java.time.LocalDateTime;
import java.util.UUID;

import static co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion.RECARGA;
import static co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion.RETIRO;

public class RetiroViewController {

        @FXML private AnchorPane ap_retiro;
        @FXML private Text txt_titulo, txt_subtitulo, txt_subtitulo1;
        @FXML private ImageView img_logo;

        @FXML private TableView<Cuenta> tb_listaCuentas;
        @FXML private TableColumn<Cuenta, String> tbc_nombreBanco, tbc_numeroCuenta, tbc_tipo;
        @FXML private TableColumn<Cuenta, Number> tbc_monto;

        @FXML private TextField txf_monto, txf_descripcion;
        @FXML private ComboBox<Categoria> cmb_categoria;

        @FXML private Button btn_volver, btn_realizarRetiro, btn_actualizar;

        private App app;
        private Usuario usuarioActual;
        private ObservableList<Cuenta> cuentas = FXCollections.observableArrayList();

        public void setApp(App app) {
                this.app = app;
                usuarioActual = (Usuario) app.getSesionActual();
                if (usuarioActual == null) {
                        app.openLoginUsuario();
                        return;
                }
                inicializarTabla();
                cargarCategorias();
                actualizarTabla();
        }

        private void inicializarTabla() {
                tbc_nombreBanco.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreBanco()));
                tbc_numeroCuenta.setCellValueFactory(c -> new SimpleStringProperty());
                tbc_monto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMonto()));
                tbc_tipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().name()));
                tb_listaCuentas.setItems(cuentas);
        }

        private void cargarCategorias() {
                cmb_categoria.setItems(FXCollections.observableArrayList(app.billetera.getGestorCategorias().listarCategorias()));
        }

        private void actualizarTabla() {
                cuentas.setAll(usuarioActual.getCuentas());
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openTransaccion();
        }

        @FXML
        void onActualizar(ActionEvent event) {
                actualizarTabla();
        }

        @FXML
        void onRealizarRetiro(ActionEvent event) {
                Cuenta cuentaSeleccionada = tb_listaCuentas.getSelectionModel().getSelectedItem();
                if (cuentaSeleccionada == null) {
                        mostrarAlerta("Error", "Selecciona una cuenta para realizar el retiro.");
                        return;
                }

                String descripcion = txf_descripcion.getText();
                Categoria categoria = cmb_categoria.getValue();

                double monto;
                try {
                        monto = Double.parseDouble(txf_monto.getText());
                        if (monto <= 0) {
                                mostrarAlerta("Error", "El monto debe ser mayor que cero.");
                                return;
                        }
                } catch (NumberFormatException e) {
                        mostrarAlerta("Error", "Monto inválido.");
                        return;
                }

                if (categoria == null) {
                        mostrarAlerta("Error", "Selecciona una categoría.");
                        return;
                }

                try {
                        // En el retiro, el destinatario puede ser null o dejarse como está en tu lógica.
                        Transaccion transaccion = new Transaccion.Builder()
                                .withFechaHora(LocalDateTime.now())
                                .withMonto(monto)
                                .withDescripcion(descripcion)
                                .withTipo(RETIRO)
                                .withCategoria(categoria)
                                .withDestinatario(usuarioActual)
                                .build();

                        mostrarAlerta("Éxito", "Retiro realizado con éxito.");
                        limpiarCampos();
                        actualizarTabla();
                } catch (Exception e) {
                        mostrarAlerta("Error", e.getMessage());
                }
        }

        private void limpiarCampos() {
                txf_descripcion.clear();
                txf_monto.clear();
                cmb_categoria.getSelectionModel().clearSelection();
                tb_listaCuentas.getSelectionModel().clearSelection();
        }

        private void mostrarAlerta(String titulo, String mensaje) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titulo);
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.showAndWait();
        }
}