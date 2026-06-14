package ni.edu.uam.ComprensionVerbalG6.acciones;

import ni.edu.uam.ComprensionVerbalG6.calculadores.PuntajeCalculator;
import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import org.openxava.actions.*;
import org.openxava.jpa.*;

public class FinalizarSesionAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        SesionEvaluacion sesion = (SesionEvaluacion) getView().getEntity();
        sesion.setFechaFin(java.time.LocalDateTime.now());

        int puntaje = PuntajeCalculator.calcular(sesion);
        sesion.setPuntajeTotal(puntaje);
        XPersistence.getManager().merge(sesion);

        getView().setValue("fechaFin", sesion.getFechaFin());
        getView().setValue("puntajeTotal", sesion.getPuntajeTotal());

        addMessage("Sesión finalizada con éxito. Puntaje obtenido: " + puntaje);
    }
}