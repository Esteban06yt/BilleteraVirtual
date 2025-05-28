package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UsuarioViewController {

        @FXML private TableView<Usuario> tb_usuario;
        @FXML private TableColumn<Usuario, String> tbc_nombreUsuario, tbc_cedulaUsuario, tbc_telefonoUsuario, tbc_correoUsuario, tbc_direccionUsuario;
        @FXML private TableColumn<Usuario, Number> tbc_saldoUsuario;

        @FXML private TableView<Cuenta> tb_listaCuentas;
        @FXML private TableColumn<Cuenta, String> tbc_nombreBancoCuenta, tbc_tipoCuenta;
        @FXML private TableColumn<Cuenta, Number> tbc_numeroCuenta, tbc_montoCuenta;

        @FXML private TableView<Presupuesto> tb_listaPresupuestos;
        @FXML private TableColumn<Presupuesto, String> tbc_nombrePresupuesto, tbc_categoriaPresupuesto;
        @FXML private TableColumn<Presupuesto, Number> tbc_montoAsignadoPresupuesto, tbc_montoGastadoPresupuesto, tbc_montoRestantePresupuesto;

        @FXML private TableView<Transaccion> tb_listaTransacciones;
        @FXML private TableColumn<Transaccion, String> tbc_descripcionTransaccion, tbc_tipoTransaccion, tbc_categoriaTransaccion, tbc_destinatarioTransaccion;
        @FXML private TableColumn<Transaccion, Number> tbc_montoTransaccion;
        @FXML private TableColumn<Transaccion, String> tbc_fechaTransaccion;

        @FXML private Button btn_volver, btn_modificarPerfil, btn_realizarTransaccion, btn_crudPresupuestos, btn_generarReporte, btn_actualizar,btn_categorizarTransaccion,
                btn_actualizar1, btn_limpiarCampos;

        @FXML private Text txt_titulo, txt_subtitulo, txt_subtitulo1, txt_subtitulo2;



        private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        App app;

        public void setApp(App app) {
                this.app = app;
        }

        @FXML
        void initialize() {

                Object sesion = App.getSesionActual();
                if (!(sesion instanceof Usuario usuario)) {
                        // Si no hay usuario, volvemos al login

                        return ;
                }

                initBindings();
                cargarDatos(usuario);
        }

        private void initBindings() {
                // Usuario
                tbc_nombreUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreCompleto()));
                tbc_cedulaUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCedula()));
                tbc_telefonoUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTelefono()));
                tbc_correoUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreo()));
                tbc_direccionUsuario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDireccion()));
                tbc_saldoUsuario.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSaldo()));

                // Cuentas
                tbc_nombreBancoCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombreBanco()));
                tbc_numeroCuenta.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getNumeroCuenta()));
                tbc_montoCuenta.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMonto()));
                tbc_tipoCuenta.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().name()));

                // Presupuestos
                tbc_nombrePresupuesto.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
                tbc_categoriaPresupuesto.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCategoria().getNombre()));
                tbc_montoAsignadoPresupuesto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMontoAsignado()));
                tbc_montoGastadoPresupuesto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMontoGastado()));
                tbc_montoRestantePresupuesto.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().calcularDisponible()));

                // Transacciones
                tbc_fechaTransaccion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFechaHora().format(DATE_FORMAT)));
                tbc_montoTransaccion.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getMonto()));
                tbc_descripcionTransaccion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion()));
                tbc_tipoTransaccion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().name()));
                tbc_categoriaTransaccion.setCellValueFactory(c -> new SimpleStringProperty(
                        c.getValue().getCategoria() != null ? c.getValue().getCategoria().getNombre() : "Sin categoría"
                ));
                tbc_destinatarioTransaccion.setCellValueFactory(c -> new SimpleStringProperty(
                        c.getValue().getDestinatario() != null ? c.getValue().getDestinatario().getNombreCompleto() : "-"
                ));
        }

        private void cargarDatos(Usuario usuario) {
                // Tabla Usuario (lista con un solo elemento)
                tb_usuario.setItems(FXCollections.observableArrayList(usuario));

                // Cuentas
                List<Cuenta> cuentas = App.billetera.getGestorCuentas().obtenerCuentasDeUsuario(usuario);
                tb_listaCuentas.setItems(FXCollections.observableList(cuentas));

                // Presupuestos
                List<Presupuesto> presupuestos = App.billetera.getGestorPresupuestos().obtenerPresupuestosDeUsuario(usuario);
                tb_listaPresupuestos.setItems(FXCollections.observableList(presupuestos));

                // Transacciones
                List<Transaccion> transacciones = App.billetera.getGestorTransacciones().obtenerTransaccionesPorUsuario(usuario);
                tb_listaTransacciones.setItems(FXCollections.observableList(transacciones));
        }

        // ----------------------------------
        // Métodos de navegación
        // ----------------------------------

        @FXML
        void onVolver(ActionEvent event) {
                app.openLoginUsuario();
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
        void onGenerarReporte(ActionEvent event) throws IOException {
                App.billetera.getGestorReportes().exportarTransaccionesACSV(
                        App.billetera.getGestorTransacciones().listarTransacciones(),
                        "transacciones.csv"
                );
                App.billetera.getGestorReportes().exportarTransaccionesAPDF(App.billetera.getGestorTransacciones().listarTransacciones(), "transacciones pdf");
        }

        @FXML
        public void onCategorizarTransaccion(ActionEvent actionEvent) {
        }

        @FXML
        public void onActualizar(ActionEvent actionEvent) {
        }

        @FXML
        public void onLimpiarCampos(ActionEvent actionEvent) {
        }

}