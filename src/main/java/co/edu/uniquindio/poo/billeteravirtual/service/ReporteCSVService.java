package co.edu.uniquindio.poo.billeteravirtual.service;

import  co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class ReporteCSVService {
    public void generarReporteTransacciones(List<Transaccion> transacciones, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaArchivo));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("ID", "Fecha", "Tipo", "Monto", "Descripción", "Categoría"))) {
            for (Transaccion t : transacciones) {
                csvPrinter.printRecord(t.getIdTransaccion(), t.getFechaHora(), t.getTipo(), t.getMonto(), t.getDescripcion(), t.getCategoria().getNombre());
            }
            csvPrinter.flush();
        }
    }
}