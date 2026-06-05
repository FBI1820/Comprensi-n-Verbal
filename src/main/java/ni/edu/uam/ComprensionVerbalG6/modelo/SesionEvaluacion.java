package ni.edu.uam.ComprensionVerbalG6.modelo;

import org.openxava.annotations.ListProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.openxava.annotations.*;
import java.util.*;

@Entity
@Table(name = "sesion_evaluacion")
@Tab(properties = "id, nombreEvaluado, fechaInicio, fechaFin, puntajeTotal")
public class SesionEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ListProperties("pregunta.numero, pregunta.textoPrincipal, opcionElegida.letra")
    private List<RespuestaSesion> respuestas = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreEvaluado() { return nombreEvaluado; }
    public void setNombreEvaluado(String n) { this.nombreEvaluado = n; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime f) { this.fechaInicio = f; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime f) { this.fechaFin = f; }

    public Integer getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Integer p) { this.puntajeTotal = p; }

    public List<RespuestaSesion> getRespuestas() { return respuestas; }
    public void setRespuestas(List<RespuestaSesion> r) { this.respuestas = r; }
}