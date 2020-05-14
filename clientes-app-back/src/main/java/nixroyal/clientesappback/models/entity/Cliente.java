package nixroyal.clientesappback.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public @Entity class Cliente implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @NotEmpty(message = "no puede estar vacio pe on")
    @Size(min = 4, max = 12, message = "debe estar en un rango de 4 y 12 caracteres pe on")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "no puede estar vacio pe on")
    private String apellido;

    @NotEmpty(message = "no puede estar vacio pe on")
    @Email(message = "no es un una dirección de correo bien formada")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "no puede estar vacio")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String foto;

    @NotNull(message = "La región no puede ser nula/vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;

    @JsonIgnoreProperties(value = { "cliente", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }

    // @PrePersist
    public void PrePersist() {
        createAt = new Date();
    }

    private static final long serialVersionUID = 1L;
}
