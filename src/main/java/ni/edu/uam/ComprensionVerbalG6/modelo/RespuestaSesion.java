package ni.edu.uam.ComprensionVerbalG6.modelo;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.openxava.annotations.*;
import javax.persistence.*;

@Entity
@Table(name = "respuesta_sesion")
public class RespuestaSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sesion_id", nullable = false)
    @NoCreate @NoModify @NoSearch
    private SesionEvaluacion sesion;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    @NoCreate @NoModify
    @DescriptionsList(descriptionProperties = "numero, textoPrincipal")
    private Pregunta pregunta;

    @ManyToOne
    @JoinColumn(name = "opcion_id")
    @DescriptionsList(descriptionProperties = "letra, texto")
    private Opcion opcionElegida;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public SesionEvaluacion getSesion() { return sesion; }
    public void setSesion(SesionEvaluacion s) { this.sesion = s; }

    public Pregunta getPregunta() { return pregunta; }
    public void setPregunta(Pregunta p) { this.pregunta = p; }

    public Opcion getOpcionElegida() { return opcionElegida; }
    public void setOpcionElegida(Opcion o) { this.opcionElegida = o; }
}