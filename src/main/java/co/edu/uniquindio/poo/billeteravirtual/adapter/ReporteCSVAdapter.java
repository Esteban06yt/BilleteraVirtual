package co.edu.uniquindio.poo.billeteravirtual.adapter;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.ReporteAdapter;
import co.edu.uniquindio.poo.billeteravirtual.service.ReporteCSVService;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;
import java.io.IOException;
import java.util.List;

public class ReporteCSVAdapter implements ReporteAdapter {
    private final ReporteCSVService service = new ReporteCSVService();

    @Override
    public void generar(List<Transaccion> transacciones, String rutaArchivo) throws IOException {
        service.generarReporteTransacciones(transacciones, rutaArchivo);
    }
}