package nixroyal.clientesappback.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public @Entity class Factura implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    private String descripcion;

    private String observacion;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createAt;

    // private LocalDateTime updateAt;

    @JsonIgnoreProperties(value = { "factura", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items;

    public Double getTotal() {
        Double total = 0.00;
        for (ItemFactura item : items) {
            total += item.getImporte();
        }
        return total;
    }

    public Factura() {
        items = new ArrayList<>();
    }

    // @PreUpdate
    // public void onUpdate() {
    // this.updateAt = LocalDateTime.now();
    // }

    private static final long serialVersionUID = 1L;
}
