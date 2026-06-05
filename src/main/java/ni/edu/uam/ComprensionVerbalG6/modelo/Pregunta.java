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

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ListProperties("letra, texto, esCorrecta")
    @EditOnly
    private List<Opcion> opciones = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getTextoPrincipal() { return textoPrincipal; }
    public void setTextoPrincipal(String t) { this.textoPrincipal = t; }

    public List<Opcion> getOpciones() { return opciones; }
    public void setOpciones(List<Opcion> o) { this.opciones = o; }
}