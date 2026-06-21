package ni.edu.uam.ComprensionVerbalG6.calculadores;

import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import org.openxava.jpa.XPersistence;
import javax.persistence.Query;
import java.time.Duration;

public class PuntajeCalculator {

    public static int calcular(SesionEvaluacion sesion) {

        // si no hay sesión o no hay fecha de inicio, el puntaje es cero
        if (sesion == null || sesion.getFechaInicio() == null || sesion.getFechaFin() == null) {
            return 0;
        }

        // validación de los 15 minutos
        long minutosTranscurridos = Duration.between(sesion.getFechaInicio(), sesion.getFechaFin()).toMinutes();

        if (minutosTranscurridos > 15) {
            return 0; // penalización automática si tardo más de 15 min, saca 0.
        }

        try {
            String jpql = "SELECT COUNT(r) FROM RespuestaSesion r " +
                    "WHERE r.sesion.id = :sesionId " +
                    "AND r.opcionElegida.esCorrecta = true";

            Query query = XPersistence.getManager().createQuery(jpql);
            query.setParameter("sesionId", sesion.getId());

            // conteo de la base de datos a un número entero
            Long respuestasCorrectas = (Long) query.getSingleResult();

            return respuestasCorrectas.intValue();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}