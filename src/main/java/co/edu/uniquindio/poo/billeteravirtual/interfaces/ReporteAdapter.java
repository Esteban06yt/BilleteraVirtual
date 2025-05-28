package co.edu.uniquindio.poo.billeteravirtual.interfaces;

import java.io.IOException;
import java.util.List;
import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

public interface ReporteAdapter {
    void generar(List<Transaccion> transacciones, String rutaArchivo) throws IOException;
}