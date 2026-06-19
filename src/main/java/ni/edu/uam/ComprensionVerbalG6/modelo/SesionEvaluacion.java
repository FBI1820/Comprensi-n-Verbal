package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ListProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.openxava.annotations.*;
import java.util.*;
@Getter
@Setter
@Entity
@Table(name = "sesion_evaluacion")
@Tab(properties = "id, nombreEvaluado, fechaInicio, fechaFin, puntajeTotal")
public class SesionEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @ReadOnly
    private Long id;

    @Column(name = "nombre_evaluado", nullable = false, length = 200)
    private String nombreEvaluado;

    @Column(name = "fecha_inicio")
    @ReadOnly
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    @ReadOnly
    private LocalDateTime fechaFin;

    @Column(name = "puntaje_total")
    @ReadOnly
    private Integer puntajeTotal;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    @ListProperties("pregunta.numero, pregunta.textoPrincipal, opcionElegida.letra, opcionElegida.texto")
    private List<RespuestaSesion> respuestas = new ArrayList<>();

    @PrePersist
    private void registrarFechaInicio() {
        this.fechaInicio = LocalDateTime.now();
    }

    public void registrarFechaFin() {
        this.fechaFin = LocalDateTime.now();
    }

    public void agregarRespuesta(RespuestaSesion respuesta) {
        respuestas.add(respuesta);
        respuesta.setSesion(this);
    }

}