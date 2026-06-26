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
    private Long id;

    // Relación con el Participante (Paciente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id")
    @DescriptionsList(descriptionProperties = "nombre") // Para que OpenXava muestre un combo con el nombre
    private Participante participante;

    // Relación con el Test ejecutado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    @DescriptionsList(descriptionProperties = "nombre")
    private Test test;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "puntaje_total")
    private Integer puntajeTotal;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    @ListProperties("pregunta.numero, pregunta.textoPrincipal, opcionElegida.letra")
    private List<RespuestaSesion> respuestas = new ArrayList<>();
}