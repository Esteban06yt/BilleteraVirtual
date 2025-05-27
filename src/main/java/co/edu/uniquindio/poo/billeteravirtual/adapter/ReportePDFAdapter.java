package co.edu.uniquindio.poo.billeteravirtual.adapter;

import co.edu.uniquindio.poo.billeteravirtual.interfaces.ReporteAdapter;
import co.edu.uniquindio.poo.billeteravirtual.service.ReportePDFService;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;
import java.io.IOException;
import java.util.List;

public class ReportePDFAdapter implements ReporteAdapter {
    private final ReportePDFService service = new ReportePDFService();

    @Override
    public void generar(List<Transaccion> transacciones, String rutaArchivo) throws IOException {
        service.generarReporteTransacciones(transacciones, rutaArchivo);
    }
}