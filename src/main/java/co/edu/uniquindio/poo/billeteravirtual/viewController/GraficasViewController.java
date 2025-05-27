package co.edu.uniquindio.poo.billeteravirtual.viewController;

import co.edu.uniquindio.poo.billeteravirtual.App;
import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorEstadisticas;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Map;

public class GraficasViewController {

    @FXML private PieChart gastosChart;
    @FXML private ComboBox<Usuario> usuarioCombo;
    public void initialize() {
        // Aquí deberías inyectar/obtener la instancia de GestorEstadisticas
        // gestorEstadisticas = ...
        usuarioCombo.setOnAction(e -> actualizarGrafica());
    }

    private void actualizarGrafica() {
        Usuario usuario = usuarioCombo.getValue();
        Map<Categoria, Double> datos = gestorEstadisticas.obtenerGastosPorCategoria(usuario);
        gastosChart.getData().clear();
        datos.forEach((cat, monto) -> {
            PieChart.Data slice = new PieChart.Data(cat.getNombre(), monto);
            gastosChart.getData().add(slice);
        });
    }

    private GestorEstadisticas gestorEstadisticas;
    @FXML
    private NumberAxis y_numeroGastos;

    @FXML
    private StackedBarChart<?, ?> stacked_gastosMasComunes;

    @FXML
    private LineChart<?, ?> line_saldoPromedio;

    @FXML
    private Button btn_volver;

    @FXML
    private Text txt_subtitulo;

    @FXML
    private NumberAxis y_numeroTransacciones;

    @FXML
    private Text txt_titulo;

    @FXML
    private AnchorPane ap_graficas;

    @FXML
    private CategoryAxis x_usarios;

    @FXML
    private NumberAxis y_saldo;

    @FXML
    private ImageView img_logo;

    @FXML
    private CategoryAxis x_tipoGasto;

    @FXML
    private CategoryAxis x_usuarioTransacciones;

    @FXML
    private Button btn_actualizar;

    @FXML
    private StackedBarChart<?, ?> stacked_usuarioMasTransacciones;

    @FXML
    void onActualizar(ActionEvent event) {

    }

    @FXML
    void onVolver(ActionEvent event) {
        app.openAdministrador();
    }


    App app;

    public void setApp(App app) {
        this.app = app;
    }

}