package co.edu.uniquindio.poo.billeteravirtual.controller;

import co.edu.uniquindio.poo.billeteravirtual.model.Categoria;
import co.edu.uniquindio.poo.billeteravirtual.model.GestorEstadisticas;
import co.edu.uniquindio.poo.billeteravirtual.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

import java.util.Map;

public class GraficasController {

    @FXML private PieChart gastosChart;
    @FXML private ComboBox<Usuario> usuarioCombo;

    private GestorEstadisticas gestorEstadisticas;

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
}