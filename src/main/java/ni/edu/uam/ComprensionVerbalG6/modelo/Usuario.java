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
    @Column(length = 32)
    private String password;

    @PrePersist
    @PreUpdate
    private void encriptarPassword() {
        if (this.password != null && this.password.length() != 32) {

            this.password = org.apache.commons.codec.digest.DigestUtils.md5Hex(this.password);
        }
    }


}
