package co.edu.uniquindio.poo.billeteravirtual.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

import co.edu.uniquindio.poo.billeteravirtual.adapter.ReporteCSVAdapter;
import co.edu.uniquindio.poo.billeteravirtual.adapter.ReportePDFAdapter;
import co.edu.uniquindio.poo.billeteravirtual.interfaces.ReporteAdapter;
import org.apache.commons.csv.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.*;

public class GestorReportes {

    private final ReporteAdapter csvAdapter = new ReporteCSVAdapter();
    private final ReporteAdapter pdfAdapter = new ReportePDFAdapter();
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void exportarTransaccionesACSV(List<Transaccion> transacciones, String ruta) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(ruta));
                CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID","Fecha","Tipo","Monto","Descripción","Categoría"))
        ) {
            for (Transaccion t : transacciones) {
                printer.printRecord(
                        t.getIdTransaccion(),
                        t.getFechaHora().format(FORMATO_FECHA),
                        t.getTipo().name(),
                        t.getMonto(),
                        t.getDescripcion(),
                        t.getCategoria() != null ? t.getCategoria().getNombre() : ""
                );
            }
        }
    }

    public void exportarTransaccionesAPDF(List<Transaccion> transacciones, String ruta) throws IOException {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.LETTER);
        doc.addPage(page);
        try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
            PDFont fuente = PDType1Font.HELVETICA;
            cs.beginText();
            cs.setFont(fuente, 12);
            cs.setLeading(14f);
            cs.newLineAtOffset(40, 720);
            cs.showText("Reporte de Transacciones");
            cs.newLine();
            cs.newLine();
            for (Transaccion t : transacciones) {
                String linea = String.format(
                        "%s | %s | %s | %.2f | %s | %s",
                        t.getIdTransaccion(),
                        t.getFechaHora().format(FORMATO_FECHA),
                        t.getTipo().getDescripcion(),
                        t.getMonto(),
                        t.getDescripcion(),
                        t.getCategoria() != null ? t.getCategoria().getNombre() : "Sin categoría"
                );
                cs.showText(linea);
                cs.newLine();
            }
            cs.endText();
        }
        doc.save(ruta);
        doc.close();
    }

    public void exportarACSV(List<Transaccion> transacciones, String ruta) throws IOException {
        csvAdapter.generar(transacciones, ruta);
    }

    public void exportarAPDF(List<Transaccion> transacciones, String ruta) throws IOException {
        pdfAdapter.generar(transacciones, ruta);
    }
}