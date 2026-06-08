package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.TextArea;

import javax.persistence.*;

import org.openxava.annotations.*;

@Getter
@Setter
@Entity
@Table(name = "opcion")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "letra", nullable = false, length = 1)
    private String letra;

    @Column(name = "texto", nullable = false, length = 500)
    @TextArea
    @DisplaySize(60)
    private String texto;

    @Column(name = "es_correcta", nullable = false)
    private boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    @NoCreate @NoModify @NoSearch
    private Pregunta pregunta;

}