package nixroyal.clientesappback.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nixroyal.clientesappback.models.entity.Cliente;
import nixroyal.clientesappback.models.entity.Region;

public interface ClienteDao extends JpaRepository<Cliente, Long> {
    @Query("from Region")
    public List<Region> findAllRegiones();
}
