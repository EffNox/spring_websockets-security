package nixroyal.clientesappback.models.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import nixroyal.clientesappback.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByNombreStartingWithIgnoreCase(String nombre);
}
