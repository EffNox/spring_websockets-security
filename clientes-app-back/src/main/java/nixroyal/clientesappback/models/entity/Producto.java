package nixroyal.clientesappback.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import lombok.Data;

@Data
public @Entity class Producto implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private String nombre;
    private Double precio;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @PreUpdate
    public void onUpdate() {
        // LocalDateTime localDateTime = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    private static final long serialVersionUID = 1L;
}
