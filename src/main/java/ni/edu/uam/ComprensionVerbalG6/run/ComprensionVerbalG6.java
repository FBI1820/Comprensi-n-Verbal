   package ni.edu.uam.ComprensionVerbalG6.run;

import org.openxava.util.*;

/**
 * Ejecuta esta clase para arrancar la aplicación.
 */
public class ComprensionVerbalG6 {

	public static void main(String[] args) throws Exception {
		// DBServer.start("ComprensionVerbalG6-db"); // COMENTADA, PostgreSQL
		AppServer.run("ComprensionVerbalG6");
	}

}