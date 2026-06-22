package ni.edu.uam.ComprensionVerbalG6.modelo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "evaluador")
public class Evaluador extends Usuario {

    @Column(length = 100, name = "especialidad")
    private String especialidad;

}
