package ni.edu.uam.ComprensionVerbalG6.modelo;


import org.openxava.annotations.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "pregunta")
@Tab(properties = "numero, textoPrincipal")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false, unique = true)
    private int numero;

    @Column(name = "texto_principal", nullable = false, length = 600)
    @TextArea
    @DisplaySize(70)
    @LabelFormat(LabelFormatType.NORMAL)
    private String textoPrincipal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    @DescriptionsList(descriptionProperties = "tipoTest") // Crea un menú desplegable en OpenXava
    private Test test;


    public Test getTest() { return test; }
    public void setTest(Test test) { this.test = test; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getTextoPrincipal() { return textoPrincipal; }
    public void setTextoPrincipal(String t) { this.textoPrincipal = t; }

    public List<Opcion> getOpciones() { return opciones; }
    public void setOpciones(List<Opcion> o) { this.opciones = o; }
}