package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CategoriaViewController {

        @FXML private AnchorPane ap_categoria;
        @FXML private Text txt_titulo, txt_subtitulo;
        @FXML private ImageView img_logo;

        @FXML private TableView<Categoria> tb_listaCategorias;
        @FXML private TableColumn<Categoria, String> tbc_nombre, tbc_descripcion;

        @FXML private TextField txf_nombre, txf_descripcion;

        @FXML private Button btn_agregarCategoria, btn_eliminarCategoria, btn_actualizar, btn_volver;

        private App app;
        private ObservableList<Categoria> categorias = FXCollections.observableArrayList();

        public void setApp(App app) {
                this.app = app;
                inicializarTabla();
                actualizarTabla();
        }

        private void inicializarTabla() {
                tbc_nombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
                tbc_descripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
                tb_listaCategorias.setItems(categorias);
        }

        private void actualizarTabla() {
                categorias.setAll(app.billetera.getGestorCategorias().listarCategorias());
        }

        @FXML
        void onVolver(ActionEvent event) {
                app.openUsuario();
        }

        @FXML
        void onAgregarCategoria(ActionEvent event) {
                String nombre = txf_nombre.getText().trim();
                String descripcion = txf_descripcion.getText().trim();

                if(nombre.isEmpty()) {
                        mostrarAlerta("Error", "El nombre de la categoría es obligatorio.");
                        return;
                }

                // Podrías agregar aquí validación para nombre único, etc.

                Categoria nueva = new Categoria(nombre, descripcion);
                boolean agregado = app.billetera.getGestorCategorias().agregarCategoria(nueva);

                if (agregado) {
                        mostrarAlerta("Éxito", "Categoría agregada correctamente.");
                        limpiarCampos();
                        actualizarTabla();
                } else {
                        mostrarAlerta("Error", "No se pudo agregar la categoría (quizá ya existe).");
                }
        }

        @FXML
        void onEliminarCategoria(ActionEvent event) {
                Categoria seleccionada = tb_listaCategorias.getSelectionModel().getSelectedItem();
                if (seleccionada == null) {
                        mostrarAlerta("Error", "Selecciona una categoría para eliminar.");
                        return;
                }

                boolean eliminado = app.billetera.getGestorCategorias().eliminarCategoria(String.valueOf(seleccionada));
                if(eliminado) {
                        mostrarAlerta("Éxito", "Categoría eliminada correctamente.");
                        actualizarTabla();
                } else {
                        mostrarAlerta("Error", "No se pudo eliminar la categoría.");
                }
        }

        @FXML
        void onActualizar(ActionEvent event) {
                actualizarTabla();
        }

        private void limpiarCampos() {
                txf_nombre.clear();
                txf_descripcion.clear();
        }

        private void mostrarAlerta(String titulo, String mensaje) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titulo);
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.showAndWait();
        }
}