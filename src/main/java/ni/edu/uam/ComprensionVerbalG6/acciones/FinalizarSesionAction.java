package ni.edu.uam.ComprensionVerbalG6.acciones;

import ni.edu.uam.ComprensionVerbalG6.calculadores.PuntajeCalculator;
import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import org.openxava.actions.*;

public class FinalizarSesionAction extends ViewBaseAction {

    public void execute() throws Exception {
        SesionEvaluacion sesion = (SesionEvaluacion) getView().getEntity();

        int puntaje = PuntajeCalculator.calcular(sesion);
        sesion.setPuntajeTotal(puntaje);
        sesion.setFechaFin(new java.util.Date());



        addMessage("Sesi?n finalizada. Puntaje obtenido: " + puntaje + " de 30");
    }
}