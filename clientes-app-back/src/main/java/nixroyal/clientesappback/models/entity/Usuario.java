package nixroyal.clientesappback.models.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
public @Entity class Usuario implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(unique = true, length = 15)
    private String username;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private @Email(message = "formato invalido") String email;

    @Column(length = 60)
    private String password;

    private Boolean state;

    // @JoinTable(name = "users_authorities",joinColumns = @JoinColumn(name =
    // "user_id"),inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "usuario_id", "perfil_id" }) })
    private List<Perfil> perfil;

    private static final long serialVersionUID = 1L;

}
