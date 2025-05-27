package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DepositoViewController {

        @FXML
        private Button btn_realizarDeposito;

        @FXML
        private TextField txf_descripcion;

        @FXML
        private TableColumn<?, ?> tbc_nombreBanco;

        @FXML
        private TableColumn<?, ?> tbc_numeroCuenta;

        @FXML
        private Button btn_volver;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private AnchorPane ap_deposito;

        @FXML
        private Text txt_titulo;

        @FXML
        private TextField txf_monto;

        @FXML
        private TableColumn<?, ?> tbc_monto;

        @FXML
        private ImageView img_logo;

        @FXML
        private TableColumn<?, ?> tbc_tipo;

        @FXML
        private TableView<?> tb_listaCuentas;

        @FXML
        private Button btn_actualizar;

        @FXML
        private ComboBox<?> cmb_categoria;

        @FXML
        private Text txt_subtitulo1;

        @FXML
        void onVolver(ActionEvent event) {
                app.openTransaccion();
        }

        @FXML
        void onRealizarDeposito(ActionEvent event) {

        }

        @FXML
        void onActualizar(ActionEvent event) {

        }

        App app;

        public void setApp(App app) {
                this.app = app;
        }

}