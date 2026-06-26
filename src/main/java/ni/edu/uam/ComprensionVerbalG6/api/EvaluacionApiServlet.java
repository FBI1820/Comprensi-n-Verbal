package ni.edu.uam.ComprensionVerbalG6.api;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import org.openxava.jpa.*;
import com.google.gson.*;
import ni.edu.uam.ComprensionVerbalG6.modelo.*;
@WebServlet("/api/evaluacion/guardar")
public class EvaluacionApiServlet  extends HttpServlet {

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // 1. Leer el JSON enviado por el HTML usando Gson
            BufferedReader reader = request.getReader();
            JsonObject jsonPayload = JsonParser.parseReader(reader).getAsJsonObject();

            Long participanteId = jsonPayload.get("participanteId").getAsLong();
            Long testId = jsonPayload.get("testId").getAsLong();
            JsonArray respuestasJson = jsonPayload.getAsJsonArray("respuestas");

            // 2. Iniciar transacción con la base de datos
            XPersistence.getManager().getTransaction().begin();

            Participante participante = XPersistence.getManager().find(Participante.class, participanteId);
            Test test = XPersistence.getManager().find(Test.class, testId);

            // 3. Crear la Sesión de Evaluación
            SesionEvaluacion sesion = new SesionEvaluacion();
            sesion.setParticipante(participante);
            sesion.setTest(test);
            sesion.setFechaInicio(LocalDateTime.now()); // Seteamos la fecha actual

            XPersistence.getManager().persist(sesion);

            int aciertos = 0;

            // 4. Procesar las 30 respuestas dinámicamente
            for (JsonElement elemento : respuestasJson) {
                JsonObject respObj = elemento.getAsJsonObject();
                int numPregunta = respObj.get("numeroPregunta").getAsInt();
                String letraOpcion = respObj.get("letraOpcion").getAsString();

                Pregunta pregunta = XPersistence.getManager()
                        .createQuery("select p from Pregunta p where p.test.id = :testId and p.numero = :num", Pregunta.class)
                        .setParameter("testId", testId)
                        .setParameter("num", numPregunta)
                        .getSingleResult();

                Opcion opcionElegida = null;
                if (!"-".equals(letraOpcion)) {
                    try {
                        opcionElegida = XPersistence.getManager()
                                .createQuery("select o from Opcion o where o.pregunta.id = :preguntaId and o.letra = :letra", Opcion.class)
                                .setParameter("preguntaId", pregunta.getId())
                                .setParameter("letra", letraOpcion)
                                .getSingleResult();

                        if (opcionElegida.isEsCorrecta()) {
                            aciertos++;
                        }
                    } catch (Exception e) {
                        // Opcion no encontrada
                    }
                }

                RespuestaSesion respuestaSesion = new RespuestaSesion();
                respuestaSesion.setSesion(sesion);
                respuestaSesion.setPregunta(pregunta);
                respuestaSesion.setOpcionElegida(opcionElegida);
                XPersistence.getManager().persist(respuestaSesion);
            }

            // LE ASIGNAMOS EL PUNTAJE CALCULADO A LA SESIÓN ANTES DEL COMMIT
            sesion.setPuntajeTotal(aciertos);
            sesion.setFechaFin(LocalDateTime.now());

            // 5. Confirmar cambios
            XPersistence.getManager().getTransaction().commit();

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"status\": \"success\", \"aciertos\": " + aciertos + "}");

        } catch (Exception e) {
            // BLOQUE CATCH QUE HACÍA FALTA PARA CAPTURAR ERRORES Y MANEJAR EL ROLLBACK
            if (XPersistence.getManager().getTransaction().isActive()) {
                XPersistence.getManager().getTransaction().rollback();
            }
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}