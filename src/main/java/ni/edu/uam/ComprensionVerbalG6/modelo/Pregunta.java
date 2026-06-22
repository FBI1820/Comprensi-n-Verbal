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
    @Hidden
    @ReadOnly
    private Long id;

    @Column(name = "numero", nullable = false)
    private int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    @DescriptionsList(descriptionProperties = "tipoTest")
    private Test test;

    @Column(name = "texto_principal", nullable = false, length = 600)
    @TextArea
    @DisplaySize(70)
    @LabelFormat(LabelFormatType.NORMAL)
    private String textoPrincipal;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ListProperties("letra, texto, esCorrecta")
    private List<Opcion> opciones = new ArrayList<>();
}