package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.enums.TipoTransaccion;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

public class TransaccionViewController {

    @FXML private AnchorPane ap_transaccion;
    @FXML private Text txt_transaccion;
    @FXML private Text txt_tipo;
    @FXML private ComboBox<TipoTransaccion> cmb_tipo;
    @FXML private ImageView img_logo;
    @FXML private javafx.scene.control.Button btn_realizar;
    @FXML private javafx.scene.control.Button btn_volver;

    private App app;
    private Usuario usuarioActual;

    /**
     * Este metodo es llamado desde App justo después de cargar el controlador.
     * Obtiene el usuario de la sesión y carga el combo.
     */
    public void setApp(App app) {
        this.app = app;
        // Obtener el usuario actual de la sesión
        Object s = app.getSesionActual();
        if (s instanceof Usuario) {
            this.usuarioActual = (Usuario) s;
        }
        inicializarDatos();
    }

    /** Carga los tipos de transacción en el ComboBox */
    private void inicializarDatos() {
        cmb_tipo.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));
    }

    /** Al pulsar "Realizar", navega a la vista concreta según el tipo seleccionado */
    @FXML
    void onRealizar(ActionEvent event) {
        if (usuarioActual == null) {
            mostrarAlerta("Sesión inválida", "No hay un usuario autenticado.", Alert.AlertType.ERROR);
            return;
        }
        TipoTransaccion tipo = cmb_tipo.getValue();
        if (tipo == null) {
            mostrarAlerta("Tipo requerido", "Seleccione un tipo de transacción.", Alert.AlertType.WARNING);
            return;
        }
        // Redirigir según el tipo
        switch (tipo) {
            case DEPOSITO:
                app.openDeposito();
                break;
            case RETIRO:
                app.openRetiro();
                break;
            case TRANSFERENCIA:
                app.openTransferencia();
                break;
            case PAGO:
                app.openPago();
                break;
            case RECARGA:
                app.openRecarga();
                break;
            default:
                mostrarAlerta("Error", "Tipo de transacción no soportado.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onVolver(ActionEvent event) {
        app.openUsuario();
    }

    /** Utilidad para mostrar alertas */
    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}