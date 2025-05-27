package co.edu.uniquindio.poo.billeteravirtual.service;

import co.edu.uniquindio.poo.billeteravirtual.model.Transaccion;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportePDFService {
    public void generarReporteTransacciones(List<Transaccion> transacciones, String rutaArchivo) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Reporte de Transacciones");
            contentStream.newLine();
            contentStream.newLine();

            for (Transaccion t : transacciones) {
                String linea = String.format("ID: %s | Fecha: %s | Tipo: %s | Monto: %.2f | Descripción: %s | Categoría: %s",
                        t.getIdTransaccion(), t.getFechaHora(), t.getTipo(), t.getMonto(), t.getDescripcion(), t.getCategoria().getNombre());
                contentStream.showText(linea);
                contentStream.newLine();
            }

            contentStream.endText();
        }

        document.save(rutaArchivo);
        document.close();
    }
}