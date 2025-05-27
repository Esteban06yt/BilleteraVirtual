package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class UsuarioViewController {


        @FXML
        private TableColumn<?, ?> tbc_cedulaUsuario;

        @FXML
        private TableView<?> tb_usuario;

        @FXML
        private TableColumn<?, ?> tbc_montoAsignadoPresupuesto;

        @FXML
        private TableColumn<?, ?> tbc_nombreBancoCuenta;

        @FXML
        private TableColumn<?, ?> tbc_direccionUsuario;

        @FXML
        private Button btn_volver;

        @FXML
        private TableColumn<?, ?> tbc_correoUsuario;

        @FXML
        private Text txt_subtitulo2;

        @FXML
        private TableColumn<?, ?> tbc_saldoUsuario;

        @FXML
        private Button btn_modificarPerfil;

        @FXML
        private ComboBox<?> cmb_categoriaTransaccion;

        @FXML
        private Button btn_actualizar;

        @FXML
        private DatePicker date_fechaTransaccion;

        @FXML
        private TableColumn<?, ?> tbc_fechaTransaccion;

        @FXML
        private Text txt_subtitulo1;

        @FXML
        private Button btn_generarReporte;

        @FXML
        private TableColumn<?, ?> tbc_destinatarioTransaccion;

        @FXML
        private TableColumn<?, ?> tbc_numeroCuenta;

        @FXML
        private TableColumn<?, ?> tbc_montoTransaccion;

        @FXML
        private TableColumn<?, ?> tbc_telefonoUsuario;

        @FXML
        private Button btn_actualizar1;

        @FXML
        private ComboBox<?> cmb_TipoTransaccion;

        @FXML
        private TableColumn<?, ?> tbc_montoRestantePresupuesto;

        @FXML
        private Button btn_realizarTransaccion;

        @FXML
        private Text txt_subtitulo;

        @FXML
        private Text txt_titulo;

        @FXML
        private Button btn_crudPresupuestos;

        @FXML
        private TableView<?> tb_listaTransacciones;

        @FXML
        private TableColumn<?, ?> tbc_tipoTransaccion;

        @FXML
        private TableColumn<?, ?> tbc_categoriaTransaccion;

        @FXML
        private Button btn_categorizarTransaccion;

        @FXML
        private ImageView img_logo;

        @FXML
        private TableColumn<?, ?> tbc_nombrePresupuesto;

        @FXML
        private TableView<?> tb_listaPresupuestos;

        @FXML
        private TableView<?> tb_listaCuentas;

        @FXML
        private AnchorPane ap_usuario;

        @FXML
        private TableColumn<?, ?> tbc_montoGastadoPresupuesto;

        @FXML
        private TableColumn<?, ?> tbc_tipoCuenta;

        @FXML
        private TableColumn<?, ?> tbc_montoCuenta;

        @FXML
        private TableColumn<?, ?> tbc_nombreUsuario;

        @FXML
        private Button btn_limpiarCampos;

        @FXML
        private TableColumn<?, ?> tbc_descripcionTransaccion;

        @FXML
        void onVolver(ActionEvent event) {
                app.openLoginUsuario();
        }

        @FXML
        void onActualizar(ActionEvent event) {

        }

        @FXML
        void onModificarPerfil(ActionEvent event) {
                app.openModificarPerfil();
        }

        @FXML
        void onRealizarTransaccion(ActionEvent event) {
                app.openTransaccion();
        }

        @FXML
        void onCrudPresupuestos(ActionEvent event) {
                app.openPresupuesto();
        }

        @FXML
        void onCategorizarTransaccion(ActionEvent event) {

        }


        @FXML
        void onLimpiarCampos(ActionEvent event) {

        }


        @FXML
        void onGenerarReporte(ActionEvent event) {

        }

        App app;

        public void setApp(App app) {
                this.app = app;
        }

}