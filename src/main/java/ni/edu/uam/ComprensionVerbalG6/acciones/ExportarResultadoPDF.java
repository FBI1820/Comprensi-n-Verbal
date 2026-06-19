package ni.edu.uam.ComprensionVerbalG6.acciones;

import org.openxava.actions.*;

public class ExportarResultadoPDF extends BaseAction implements IForwardAction {

    @Override
    public void execute() throws Exception {
        System.out.println("Generando el PDF de la lista global...");
    }

    @Override
    public String getForwardURI() {
        // Redirige al Servlet que creamos al principio para procesar la descarga limpia
        return "/servlets/descargarPdf";
    }

    @Override
    public boolean inNewWindow() {
        return false; // Al ser FALSE, procesa la descarga en el mismo flujo y evita pantallas congeladas o errores 404
    }
}