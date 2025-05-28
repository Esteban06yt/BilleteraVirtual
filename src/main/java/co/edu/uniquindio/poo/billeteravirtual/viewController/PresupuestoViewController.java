package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.Presupuesto;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
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

public class PresupuestoViewController {

        @FXML private AnchorPane ap_presupuesto;
        @FXML private TableView<Presupuesto> tb_listaPresupuestos;
        @FXML private TableColumn<Presupuesto, String> tbc_nombre;
        @FXML private TableColumn<Presupuesto, Double> tbc_montoAsignado;
        @FXML private TableColumn<Presupuesto, Double> tbc_montoGastado;
        @FXML private TableColumn<Presupuesto, Double> tbc_montoRestante;
        @FXML private TableColumn<Presupuesto, String> tbc_categoria;

        @FXML private TextField txf_nombre;
        @FXML private TextField txf_montoAsignado;
        @FXML private ComboBox<Categoria> cmb_categoria;

        @FXML private Button btn_agregarPresupuesto;
        @FXML private Button btn_actualizar;
        @FXML private Button btn_eliminarPresupuesto;
        @FXML private Button btn_volver;

        @FXML private ImageView img_logo;
        @FXML private Text txt_titulo;
        @FXML private Text txt_subtitulo;

        private App app;
        private Usuario usuarioActual;
        private ObservableList<Presupuesto> presupuestos = FXCollections.observableArrayList();

        public void setApp(App app) {
                this.app = app;
                this.usuarioActual = App.usuarioActual;
                inicializarDatos();
        }

        private void inicializarDatos() {
                cmb_categoria.setItems(FXCollections.observableArrayList(usuarioActual.getCategorias()));
                if (usuarioActual != null) {
                        presupuestos.setAll(usuarioActual.getPresupuestos());
                        tb_listaPresupuestos.setItems(presupuestos);

                        tbc_nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
                        tbc_montoAsignado.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMontoAsignado()).asObject());
                        tbc_montoGastado.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMontoGastado()).asObject());
                        tbc_montoRestante.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().calcularDisponible()).asObject());
                        tbc_categoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().toString()));
                }
        }

        @FXML
        void onAgregarPresupuesto(ActionEvent event) {
                String nombre = txf_nombre.getText();
                String montoStr = txf_montoAsignado.getText();
                Categoria categoria = cmb_categoria.getValue();

                if (nombre == null || nombre.isBlank() || montoStr == null || montoStr.isBlank() || categoria == null) {
                        mostrarAlerta("Error", "Debe llenar todos los campos correctamente.");
                        return;
                }

                try {
                        double monto = Double.parseDouble(montoStr);
                        Presupuesto nuevo = new Presupuesto(nombre, monto, categoria);
                        usuarioActual.getPresupuestos().add(nuevo);
                        presupuestos.add(nuevo);
                        limpiarFormulario();
                } catch (NumberFormatException e) {
                        mostrarAlerta("Error", "El monto asignado debe ser un número válido.");
                }
        }

        @FXML
        void onActualizar(ActionEvent event) {
                Presupuesto seleccionado = tb_listaPresupuestos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                        String nombre = txf_nombre.getText();
                        Categoria categoria = cmb_categoria.getValue();
                        try {
                                double monto = Double.parseDouble(txf_montoAsignado.getText());
                                seleccionado.setNombre(nombre);
                                seleccionado.setMontoAsignado(monto);
                                seleccionado.setCategoria(categoria);
                                tb_listaPresupuestos.refresh();
                                limpiarFormulario();
                        } catch (NumberFormatException e) {
                                mostrarAlerta("Error", "El monto asignado debe ser un número válido.");
                        }
                }
        }

        @FXML
        void onEliminarPresupuesto(ActionEvent event) {
                Presupuesto seleccionado = tb_listaPresupuestos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                        usuarioActual.getPresupuestos().remove(seleccionado);
                        presupuestos.remove(seleccionado);
                }
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openUsuario();
        }

        private void limpiarFormulario() {
                txf_nombre.clear();
                txf_montoAsignado.clear();
                cmb_categoria.getSelectionModel().clearSelection();
        }

        private void mostrarAlerta(String titulo, String contenido) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(titulo);
                alert.setContentText(contenido);
                alert.showAndWait();
        }
}