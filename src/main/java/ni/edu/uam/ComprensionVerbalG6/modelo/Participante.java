package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "participante", schema = "public")
// Quitamos 'respuestas' de la vista principal si genera conflicto visual, o lo dejamos como propiedad simple
@View(members="nombre, email; tipoDocumento, idUnico; fechaNacimiento, ocupacion; respuestas")

public class Participante extends Usuario {
    @Required
    @Column(length = 30, name = "tipo_documento")
    private String tipoDocumento;

    @Required
    @Column(length = 30, name = "id_unico")
    private String idUnico;

    @Required
    @Column(length = 15, name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(length = 50, name = "ocupacion")
    private String ocupacion;

    // SOLUCIÓN: Cambiamos la colección problemática por un String plano de longitud extendida
    @Column(length = 1000, name = "respuestas")
    @TextArea // Le indica a OpenXava que muestre un cuadro de texto cómodo para el Psicólogo
    private String respuestas;
}
