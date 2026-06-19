package ni.edu.uam.ComprensionVerbalG6.acciones;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

@WebServlet("/servlets/descargarPdf")
public class DescargarResultadoPDF extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Indicamos al navegador que descargue un archivo PDF directamente
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Reporte_General_Sesiones.pdf\"");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // --- DISEÑO DEL PDF GLOBAL ---
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte General - Sesiones de Evaluacion", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" ")); // Espacio en blanco

            // Aquí creas la tabla general
            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("ID");
            tabla.addCell("Nombre del Evaluado");
            tabla.addCell("Fecha de Inicio");
            tabla.addCell("Puntaje Total");

            // Datos de prueba (Luego podrás conectarlo a tu base de datos si lo requieres)
            tabla.addCell("1"); tabla.addCell("Fabiola Lanuza"); tabla.addCell("15/06/2026"); tabla.addCell("85");
            tabla.addCell("2"); tabla.addCell("Alexandra"); tabla.addCell("13/06/2026"); tabla.addCell("90");
            tabla.addCell("3"); tabla.addCell("fabi"); tabla.addCell("15/06/2026"); tabla.addCell("75");

            document.add(tabla);
            // -----------------------------

            document.close();

        } catch (DocumentException e) {
            throw new ServletException("Error al construir el PDF global", e);
        }
    }
}