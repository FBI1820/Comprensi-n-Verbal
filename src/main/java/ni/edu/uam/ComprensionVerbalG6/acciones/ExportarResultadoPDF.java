package ni.edu.uam.ComprensionVerbalG6.acciones;

import org.openxava.actions.*;

// NOTA: Extiende de BaseAction, NO de JasperReportBaseAction
public class ExportarResultadoPDF extends BaseAction implements IForwardAction {

    @Override
    public void execute() throws Exception {
        System.out.println("====== Redirigiendo al servlet del reporte PDF ======");
    }

    @Override
    public String getForwardURI() {
        return "/servlets/descargarPdf";
    }

    @Override
    public boolean inNewWindow() {
        return true;
    }
}