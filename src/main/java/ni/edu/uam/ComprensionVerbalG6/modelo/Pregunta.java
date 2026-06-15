package ni.edu.uam.ComprensionVerbalG6.modelo;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "pregunta")
@Tab(properties = "numero, textoPrincipal")
@View(members="numero, textoPrincipal, opciones")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false, unique = true)
    private int numero;

    @ManyToOne
    @JoinColumn(name="test_id")  // nombre de la columna en la tabla Pregunta
    private Test test;

    @Column(name = "texto_principal", nullable = false, length = 600)
    @TextArea
    @DisplaySize(70)
    @LabelFormat(LabelFormatType.NORMAL)
    private String textoPrincipal;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ListProperties("letra, texto, esCorrecta")
    @EditOnly
    private List<Opcion> opciones = new ArrayList<>();


}