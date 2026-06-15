package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.openxava.annotations.*;
import javax.persistence.*;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "respuesta_sesion")
public class RespuestaSesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sesion_id", nullable = false)
    @NoCreate @NoModify @NoSearch
    private SesionEvaluacion sesion;

    @ManyToOne
    @JoinColumn(name="pregunta_id", nullable=false)
    private Pregunta pregunta;

    @ManyToOne
    @JoinColumn(name="opcion_id")
    @DescriptionsList(descriptionProperties="letra, texto") // solo muestra letra y texto
    @NoCreate @NoModify @NoSearch
    private Opcion opcionElegida;

    @Hidden
    private LocalDateTime momentoRespuesta;

    public void setOpcionElegida(Opcion opcion) {
        if (opcion != null) {
            contestar(opcion);
        } else {
            this.opcionElegida = null;
        }
    }


    public void contestar(Opcion opcion) {
        if (!sesion.dentroDelTiempo()) {
            throw new ValidationException("El tiempo de la sesión ya terminó");
        }
        this.opcionElegida = opcion;
        this.momentoRespuesta = LocalDateTime.now();
    }








}