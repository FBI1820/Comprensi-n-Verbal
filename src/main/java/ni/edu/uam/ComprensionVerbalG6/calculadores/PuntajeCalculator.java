package ni.edu.uam.ComprensionVerbalG6.calculadores;

import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import ni.edu.uam.ComprensionVerbalG6.modelo.RespuestaSesion;

public class PuntajeCalculator {

    public static int calcular(SesionEvaluacion sesion) {
        int puntaje = 0;
        if (sesion.getRespuestas() == null) return 0;
        for (RespuestaSesion r : sesion.getRespuestas()) {
            if (r.getOpcionElegida() != null && r.getOpcionElegida().isEsCorrecta()) {
                puntaje++;
            }
        }
        return puntaje;
    }
}