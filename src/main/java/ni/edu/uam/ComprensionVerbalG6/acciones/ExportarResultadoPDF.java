package ni.edu.uam.ComprensionVerbalG6.acciones;
import org.openxava.actions.*;
import net.sf.jasperreports.engine.*;
import org.openxava.jpa.XPersistence;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
public class ExportarResultadoPDF  extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        // Compilar el reporte JRXML
        JasperReport report = JasperCompileManager.compileReport("reports/Resultado.jrxml");

        // Parámetros que se pasan al reporte
        Map<String, Object> params = new HashMap<>();
        params.put("usuario", getView().getValue("usuario"));
        params.put("puntaje", getView().getValue("puntaje"));
        params.put("fecha", getView().getValue("fecha"));

        // Conexión a la BD
        Connection conn = XPersistence.getManager().unwrap(Connection.class);

        // Llenar el reporte con datos
        JasperPrint print = JasperFillManager.fillReport(report, params, conn);

        // Exportar a PDF
        JasperExportManager.exportReportToPdfFile(print, "resultado.pdf");

        addMessage("Reporte PDF generado correctamente en resultado.pdf");
    }
}



