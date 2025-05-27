package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class TransaccionViewController {

    @FXML
    private Text txt_tipo;

    @FXML
    private ImageView img_logo;

    @FXML
    private Text txt_transaccion;

    @FXML
    private Button btn_realizar;

    @FXML
    private Button btn_volver;

    @FXML
    private ComboBox<?> cmb_tipo;

    @FXML
    private AnchorPane ap_transaccion;

    @FXML
    void onRealizar(ActionEvent event) {

    }

    @FXML
    void onVolver(ActionEvent event) {
        app.openUsuario();
    }

    App app;

    public void setApp(App app) {
        this.app = app;
    }
}
