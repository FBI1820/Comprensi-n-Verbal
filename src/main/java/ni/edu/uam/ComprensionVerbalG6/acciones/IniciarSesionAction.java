package ni.edu.uam.ComprensionVerbalG6.acciones;

import ni.edu.uam.ComprensionVerbalG6.modelo.SesionEvaluacion;
import org.openxava.actions.*;

public class IniciarSesionAction extends SaveAction {

    @Override
    public void execute() throws Exception {
        SesionEvaluacion sesion = (SesionEvaluacion) getView().getEntity();

        // Registra la hora de inicio autom?ticamente al guardar
        if (sesion.getFechaInicio() == null) {
            sesion.setFechaInicio(new java.util.Date());

        }

        super.execute(); // Llama al Save normal de OpenXava
        addMessage("Sesi?n iniciada correctamente para: " + sesion.getNombreEvaluado());
    }
}