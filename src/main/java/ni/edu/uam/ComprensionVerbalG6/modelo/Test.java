package ni.edu.uam.ComprensionVerbalG6.modelo;

import org.openxava.annotations.*;
import javax.persistence.*;
import java.util.*;

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

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoTest() { return tipoTest; }
    public void setTipoTest(String tipoTest) { this.tipoTest = tipoTest; }

    public int getTiempoLimiteMinutos() { return tiempoLimiteMinutos; }
    public void setTiempoLimiteMinutos(int tiempoLimiteMinutos) { this.tiempoLimiteMinutos = tiempoLimiteMinutos; }

    public List<Pregunta> getPreguntas() { return preguntas; }
    public void setPreguntas(List<Pregunta> preguntas) { this.preguntas = preguntas; }
}