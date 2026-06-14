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
@Tab(properties = "numero, test.tipoTest, textoPrincipal")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false)
    private int numero;

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