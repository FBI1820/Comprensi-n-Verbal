package ni.edu.uam.ComprensionVerbalG6.acciones;

import ni.edu.uam.ComprensionVerbalG6.calculadores.PuntajeCalculator;
import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import org.openxava.actions.*;
import org.openxava.jpa.XPersistence;

public class FinalizarSesionAction extends ViewBaseAction {

    public void execute() throws Exception {
        SesionEvaluacion sesion = (SesionEvaluacion) getView().getEntity();

        sesion.setFechaFin(java.time.LocalDateTime.now());

        int puntaje = PuntajeCalculator.calcular(sesion);
        sesion.setPuntajeTotal(puntaje);
        //refresca la interfaz de openxava para que el user vea su resultado
        XPersistence.getManager().merge(sesion);
// muestra el mensaje con exito
        getView().refresh();

        addMessage("Sesión finalizada. Puntaje obtenido: " + puntaje + " de 30");
    }
}