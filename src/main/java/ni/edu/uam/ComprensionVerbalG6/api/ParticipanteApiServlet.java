package ni.edu.uam.ComprensionVerbalG6.api;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import org.openxava.jpa.*;
import ni.edu.uam.ComprensionVerbalG6.modelo.Participante;


@WebServlet("/api/participante/guardar")
public class ParticipanteApiServlet extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    // 2. RECIBE LOS DATOS DEL HTML Y LOS GUARDA EN POSTGRES
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configurar Cabeceras CORS para la respuesta exitosa
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el JSON que envía tu frontend de usuario
        StringBuilder sb = new StringBuilder();
        String linea;
        try (BufferedReader reader = request.getReader()) {
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }
        }

        try {
            String json = sb.toString();

            // Instanciar Participante usando tu modelo POO
            Participante nuevoParticipante = new Participante();

            // Mapeamos los datos extrayéndolos del JSON
            nuevoParticipante.setNombre(extraerValor(json, "nombreCompleto"));
            nuevoParticipante.setEmail(extraerValor(json, "email"));
            nuevoParticipante.setTipoDocumento(extraerValor(json, "tipoDocumento"));
            nuevoParticipante.setIdUnico(extraerValor(json, "idUnico"));
            nuevoParticipante.setFechaNacimiento(extraerValor(json, "fechaNacimiento"));
            nuevoParticipante.setNivelEstudios(extraerValor(json, "nivelEstudios"));
            nuevoParticipante.setOcupacion(extraerValor(json, "ocupacion"));
            nuevoParticipante.setPassword("participante123");

            String respuestasRaw = extraerValor(json, "respuestas");
            if (!respuestasRaw.isEmpty()) {
                nuevoParticipante.setRespuestas(respuestasRaw);
            }

            // Persistencia en Base de Datos integrada con OpenXava (PostgreSQL)
            XPersistence.getManager().persist(nuevoParticipante);
            XPersistence.commit();

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"status\": \"success\", \"message\": \"¡Datos guardados con éxito!\"}");

        } catch (Exception e) {
            XPersistence.rollback();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // 3. MÉTODO AUXILIAR PARA PARSEAR EL JSON
    private String extraerValor(String json, String llave) {
        try {
            String patron = "\"" + llave + "\":\"(.*?)\"";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(patron);
            java.util.regex.Matcher m = p.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
        } catch (Exception e) {
            return "";
        }
        return "";


    }

}