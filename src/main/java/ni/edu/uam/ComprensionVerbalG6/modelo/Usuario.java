package ni.edu.uam.ComprensionVerbalG6.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;

import javax.persistence.*;

@Getter@Setter
@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @Column(length = 100, nullable = false)
    @Required
    private String nombre;

    @Column(length = 100, nullable = false)
    @Required
    private String email;

    @Stereotype("PASSWORD")
    @Column(length = 64)
    private String password;

    @PrePersist
    @PreUpdate
    private void encriptarPassword() {
        // SHA-256 siempre genera una cadena de exactamente 64 caracteres
        if (this.password != null && this.password.length() != 64) {

            this.password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(this.password);

        }
    }


}