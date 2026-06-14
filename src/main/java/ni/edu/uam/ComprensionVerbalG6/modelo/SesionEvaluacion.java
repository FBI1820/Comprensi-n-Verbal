package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ListProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;

import java.util.*;
@Getter
@Setter
@Entity
@Table(name = "sesion_evaluacion")
@Tab(properties = "id, nombreEvaluado, fechaInicio, fechaFin, puntajeTotal")
public class SesionEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_evaluado", nullable = false, length = 200)
    private String nombreEvaluado;
    //cambios en fecha de inicio para hacerlo automatico

    @Column(name = "fecha_inicio")
    @ReadOnly
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @ReadOnly
    private Date fechaFin;


    @Column(name = "puntaje_total")
    @ReadOnly
    private Integer puntajeTotal;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    @ListProperties("pregunta, opcionElegida")
    private List<RespuestaSesion> respuestas = new ArrayList<>();

    @PrePersist
    public void asignarFechaInicio() {
        this.fechaInicio = new Date(); // usa java.util.Date
    }

    @PreUpdate
    public void asignarFechaFin() {
        this.fechaFin = new Date();
    }
}

