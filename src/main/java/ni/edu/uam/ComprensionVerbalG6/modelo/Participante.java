package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "participante")
public class Participante extends Usuario {

    @Column(length = 50, name = "ocupacion")
    private String ocupacion;

}
