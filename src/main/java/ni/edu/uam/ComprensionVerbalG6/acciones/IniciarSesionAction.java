package ni.edu.uam.ComprensionVerbalG6.acciones;

import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import org.openxava.actions.*;

public class IniciarSesionAction extends SaveAction {

    @Override
    public void execute() throws Exception {
        SesionEvaluacion sesion = (SesionEvaluacion) getView().getEntity();

        // Registra la hora de inicio automáticamente al guardar
        if (sesion.getFechaInicio() == null) {
            sesion.setFechaInicio(java.time.LocalDateTime.now());
        }

        super.execute(); // Llama al Save normal de OpenXava
        addMessage("Sesión iniciada correctamente para: " + sesion.getNombreEvaluado());
    }
}