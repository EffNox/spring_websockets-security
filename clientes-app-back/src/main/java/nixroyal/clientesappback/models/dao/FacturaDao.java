package nixroyal.clientesappback.models.dao;

import org.springframework.data.repository.CrudRepository;
import nixroyal.clientesappback.models.entity.Factura;

public interface FacturaDao extends CrudRepository<Factura, Long> {
}
