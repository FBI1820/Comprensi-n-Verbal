package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import ni.edu.uam.ComprensionVerbalG6.calculadores.PuntajeCalculator;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    @DescriptionsList(descriptionProperties = "tipoTest")
    private Test test;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    @ListProperties("pregunta.pregunta, opcionElegida.texto")
    private List<RespuestaSesion> respuestas = new ArrayList<>();

    @PrePersist
    private void registrarFechaInicio() {
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = fechaInicio.plusMinutes(test.getTiempoLimiteMinutos());

        this.respuestas = new ArrayList<>();
        for (Pregunta p : test.getPreguntas()) {
            RespuestaSesion r = new RespuestaSesion();
            r.setSesion(this);
            r.setPregunta(p);
            respuestas.add(r);
        }
    }


    public boolean dentroDelTiempo() {
        return LocalDateTime.now().isBefore(fechaFin);
    }

    public void finalizarSesion() {
        this.fechaFin = LocalDateTime.now();
        this.puntajeTotal = PuntajeCalculator.calcular(this);
    }



}
