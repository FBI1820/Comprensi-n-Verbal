package ni.edu.uam.ComprensionVerbalG6.acciones;

import org.apache.commons.logging.*;
import org.openxava.actions.*;

public class ExportarResultadoPDF extends BaseAction implements IForwardAction {

    private static final Log log = LogFactory.getLog(ExportarResultadoPDF.class);

    @Override
    public void execute() throws Exception {
        log.info("ejecutando redirección automatizada hacia el servlet de reportes pdf");
    }

    @Override
    public String getForwardURI() {
        // se recupera el nombre del módulo activo en openxava para enviarlo como parámetro al servlet
        String moduloActivo = getManager().getModuleName();
        return "/servlets/descargarPdf?module=" + moduloActivo;
    }

    @Override
    public boolean inNewWindow() {
        return true;
    }
}