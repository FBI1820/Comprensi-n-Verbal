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

        // 1. CAPTURAMOS DESDE QUÉ PANTALLA LE DIERON CLIC
        String moduloActual = request.getParameter("module");

        response.setContentType("application/pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);

            // 2. CAMBIAMOS EL CONTENIDO SEGÚN LA PANTALLA
            if ("Pregunta".equals(moduloActual)) {
                response.setHeader("Content-Disposition", "attachment; filename=\"Reporte_Preguntas.pdf\"");

                Paragraph titulo = new Paragraph("Reporte de Banco de Preguntas", fuenteTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);

                // Aquí armarías tu tabla con columnas para Enunciado, Respuesta, etc.

            } else {
                // POR DEFECTO: Tu reporte de Sesiones de Evaluación que ya tienes hecho
                response.setHeader("Content-Disposition", "attachment; filename=\"Reporte_General_Sesiones.pdf\"");

                Paragraph titulo = new Paragraph("Reporte General - Sesiones de Evaluacion", fuenteTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);

                document.add(new Paragraph(" "));

                PdfPTable tabla = new PdfPTable(4);
                tabla.addCell("ID"); tabla.addCell("Nombre del Evaluado"); tabla.addCell("Fecha de Inicio"); tabla.addCell("Puntaje Total");

                tabla.addCell("1"); tabla.addCell("Fabiola Lanuza"); tabla.addCell("15/06/2026"); tabla.addCell("85");
                tabla.addCell("2"); tabla.addCell("Alexandra"); tabla.addCell("13/06/2026"); tabla.addCell("90");
                tabla.addCell("3"); tabla.addCell("fabi"); tabla.addCell("15/06/2026"); tabla.addCell("75");

                document.add(tabla);
            }

            document.close();

        } catch (DocumentException e) {
            throw new ServletException("Error al construir el PDF global", e);
        }
    }
}