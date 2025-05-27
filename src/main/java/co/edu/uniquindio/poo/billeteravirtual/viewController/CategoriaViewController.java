package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class CategoriaViewController {

        @FXML
        private TextField txf_nombre;

        @FXML
        private TextField txf_descripcion;

        @FXML
        private Button btn_eliminarCategoria;

        @FXML
        private Button btn_volver;

        @FXML
        private Button btn_agregarCategoria;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private AnchorPane ap_categoria;

        @FXML
        private TableColumn<?, ?> tbc_nombre;

        @FXML
        private ImageView img_logo;

        @FXML
        private TableView<?> tb_listaCategorias;

        @FXML
        private TableColumn<?, ?> tbc_descripcion;

        @FXML
        private Button btn_actualizar;

        @FXML
        void onVolver(ActionEvent event) {
                app.openUsuario();
        }

        @FXML
        void onEliminarCategoria(ActionEvent event) {

        }

        @FXML
        void onAgregarCategoria(ActionEvent event) {

        }

        @FXML
        void onActualizar(ActionEvent event) {

        }

        App app;

        public void setApp(App app) {
                this.app = app;
        }
}