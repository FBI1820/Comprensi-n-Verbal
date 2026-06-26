package ni.edu.uam.ComprensionVerbalG6.acciones;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.openxava.jpa.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;

@WebServlet("/servlets/descargarPdf")
public class DescargarResultadoPDF extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // se obtiene el nombre del módulo de openxava para identificar la vista de origen
        String moduloActual = request.getParameter("module");

        response.setContentType("application/pdf");

        try {
            Document document = new Document(PageSize.A4, 36, 36, 54, 36);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, java.awt.Color.DARK_GRAY);
            Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, java.awt.Color.GRAY);
            Font fontHeaderTabla = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, java.awt.Color.WHITE);
            Font fontCuerpoTabla = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, java.awt.Color.BLACK);

            // validación para renderizar el reporte correspondiente al módulo activo
            if ("Pregunta".equals(moduloActual)) {
                response.setHeader("Content-Disposition", "attachment; filename=\"Reporte_Preguntas.pdf\"");

                Paragraph titulo = new Paragraph("Reporte de Banco de Preguntas", fontTitulo);
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);
                
                // estructurar la tabla específica con los campos de las preguntas (Enunciado, Opciones, etc.)

            } else {
                //caso por defecto: generación del reporte estándar para las sesiones de evaluación
                response.setHeader("Content-Disposition", "attachment; filename=\"Reporte_Sesiones_Evaluacion.pdf\"");

                Paragraph titulo = new Paragraph("Reporte General de Sesiones", fontTitulo);
                titulo.setAlignment(Element.ALIGN_LEFT);
                document.add(titulo);

                Paragraph subtitulo = new Paragraph("Generado automáticamente por el sistema de Comprensión Verbal G6", fontSubtitulo);
                document.add(subtitulo);
                document.add(Chunk.NEWLINE);

                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidthPercentage(100);
                tabla.setWidths(new float[]{15f, 45f, 25f, 15f});

                String[] columnas = {"ID", "Participante / Evaluado", "Fecha de Registro", "Puntaje"};
                for (String columna : columnas) {
                    PdfPCell cell = new PdfPCell(new Paragraph(columna, fontHeaderTabla));
                    cell.setBackgroundColor(new java.awt.Color(44, 62, 80));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(6);
                    tabla.addCell(cell);
                }

                List<SesionEvaluacion> sesiones = XPersistence.getManager()
                        .createQuery("from SesionEvaluacion", SesionEvaluacion.class)
                        .getResultList();

                if (sesiones != null && !sesiones.isEmpty()) {
                    for (SesionEvaluacion sesion : sesiones) {
                        tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(sesion.getId()), fontCuerpoTabla)));
                        
                        // se utiliza la propiedad directa mapeada en la entidad de sesiones
                        String nombre = (sesion.getNombreEvaluado() != null) ? sesion.getNombreEvaluado() : "No asignado";
                        tabla.addCell(new PdfPCell(new Paragraph(nombre, fontCuerpoTabla)));
                        
                        String fecha = (sesion.getFechaInicio() != null) ? sesion.getFechaInicio().toString() : "S/F";
                        tabla.addCell(new PdfPCell(new Paragraph(fecha, fontCuerpoTabla)));
                        
                        String puntaje = (sesion.getPuntajeTotal() != null) ? String.valueOf(sesion.getPuntajeTotal()) : "0";
                        tabla.addCell(new PdfPCell(new Paragraph(puntaje, fontCuerpoTabla)));
                    }
                } else {
                    PdfPCell celdaVacia = new PdfPCell(new Paragraph("No se encontraron registros de sesiones en la base de datos.", fontCuerpoTabla));
                    celdaVacia.setColspan(4);
                    celdaVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celdaVacia.setPadding(10);
                    tabla.addCell(celdaVacia);
                }

                document.add(tabla);
            }

            document.close();

        } catch (DocumentException e) {
            throw new ServletException("Error al construir el PDF global", e);
        }
    }
}