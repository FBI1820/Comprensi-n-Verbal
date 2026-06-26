package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openxava.annotations.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "participante")
public class Participante extends Usuario {
    @Column(name = "tipo_documento", length = 50)
    private String tipoDocumento;

    @Column(name = "id_unico", length = 30, unique = true)
    private String idUnico;

    @Column(name = "fecha_nacimiento", length = 20)
    private String fechaNacimiento;

    @Column(name = "nivel_estudios", length = 50)
    private String nivelEstudios;

    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    @Column(name = "respuestas", length = 5000)
    private String respuestas;
}
