package ni.edu.uam.ComprensionVerbalG6.modelo;

import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.TextArea;

import javax.persistence.*;

import org.openxava.annotations.*;


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

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLetra() { return letra; }
    public void setLetra(String l) { this.letra = l; }

    public String getTexto() { return texto; }
    public void setTexto(String t) { this.texto = t; }

    public boolean isEsCorrecta() { return esCorrecta; }
    public void setEsCorrecta(boolean e) { this.esCorrecta = e; }

    public Pregunta getPregunta() { return pregunta; }
    public void setPregunta(Pregunta p) { this.pregunta = p; }
}