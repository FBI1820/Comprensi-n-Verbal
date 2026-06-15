package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import javax.persistence.*;
import java.util.*;
@Getter
@Setter
@Entity
@Table(name = "test")
@Tab(properties = "tipoTest, tiempoLimiteMinutos")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @Column(name = "tipo_test", length = 100, nullable = false)
    @Required
    private String tipoTest;

    @Column(name = "tiempo_limite_minutos", nullable = false)
    @Required
    private int tiempoLimiteMinutos; // Ej: 15

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    @ListProperties("numero, textoPrincipal")
    private List<Pregunta> preguntas = new ArrayList<>();
}